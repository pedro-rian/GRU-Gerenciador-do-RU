package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.PessoaDTO;
import br.com.ufrn.imd.gru.model.Pessoa;
import br.com.ufrn.imd.gru.model.TipoUsuario;
import br.com.ufrn.imd.gru.model.Usuario;
import br.com.ufrn.imd.gru.repository.PessoaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public ResponseEntity<String> validarPessoa(PessoaDTO pessoaDTO) {

        if (pessoaDTO.getNome() == null || pessoaDTO.getNome().isEmpty() ||
                pessoaDTO.getEmail() == null || pessoaDTO.getEmail().isEmpty() ||
                pessoaDTO.getIdade() == 0 ||
                pessoaDTO.getPeso() == 0 ||
                pessoaDTO.getAltura() == 0 ||
                pessoaDTO.getSenha() == null || pessoaDTO.getSenha().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Preencha todos os campos obrigatórios.");
        }
        else if (existeUsuarioComEmail(pessoaDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Já existe um usuário com esse email.");
        }
        return null;
    }
    public boolean existeUsuarioComEmail(String email) {
        return usuarioService.existeUsuarioComEmail(email);
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
    }


    public Pessoa buscarPorIdDoUsuario(Long idUsuario) {
        Optional<Pessoa> optionalPessoa = pessoaRepository.findByUsuarioId(idUsuario);
        return optionalPessoa.orElse(null);
    }



}

