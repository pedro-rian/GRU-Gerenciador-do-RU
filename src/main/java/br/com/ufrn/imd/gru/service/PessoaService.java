package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.model.Pessoa;
import br.com.ufrn.imd.gru.model.TipoUsuario;
import br.com.ufrn.imd.gru.model.Usuario;
import br.com.ufrn.imd.gru.repository.PessoaRepository;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;
    private final  UsuarioService usuarioService;

    public PessoaService(PessoaRepository pessoaRepository, UsuarioService usuarioService) {
        this.pessoaRepository = pessoaRepository;
        this.usuarioService = usuarioService;
    }

    public double calcularIMC(Pessoa pessoa) {
        return pessoa.getPeso() / (pessoa.getAltura() * pessoa.getAltura());
    }

    public void salvarDadosEIMC(Pessoa pessoa) {
        if (pessoa.getUsuario() == null) {
            Usuario novoUsuario = new Usuario();
            pessoa.setUsuario(novoUsuario);
        }

        double imc = calcularIMC(pessoa);
        pessoa.setImc(imc);

        pessoa.getUsuario().setTipo(TipoUsuario.CONSUMIDOR);
        pessoa.getUsuario().setAtivo(true);

        Usuario usuario = pessoa.getUsuario();
        if (usuario != null) {
            usuarioService.salvarUsuario(usuario);
        } else {
        }
        pessoaRepository.save(pessoa);
        System.out.println("Usuário e dados da pessoa salvos com sucesso. IMC: " + imc);
    }


}

