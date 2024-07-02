package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.PessoaDTO;
import br.com.ufrn.imd.gru.model.Pessoa;
import br.com.ufrn.imd.gru.model.Usuario;
import br.com.ufrn.imd.gru.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository, UsuarioService usuarioService) {
        this.pessoaRepository = pessoaRepository;
        this.usuarioService = usuarioService;
    }

    public double calcularIMC(Pessoa pessoa) {
        return pessoa.getPeso() / (pessoa.getAltura() * pessoa.getAltura());
    }

    public ResponseEntity<List<String>> validarPessoa(PessoaDTO pessoaDTO) {
        List<String> errors = new ArrayList<>();

        if (pessoaDTO.getNome() == null || pessoaDTO.getNome().isEmpty()) {
            errors.add("Nome é um campo obrigatório.");
        }
        if (pessoaDTO.getEmail() == null || pessoaDTO.getEmail().isEmpty()) {
            errors.add("E-mail é um campo obrigatório.");
        }
        if (existeUsuarioComEmail(pessoaDTO.getEmail())) {
            errors.add("Já existe um usuário com esse e-mail.");
        }
        if (pessoaDTO.getIdade() == 0) {
            errors.add("Idade é um campo obrigatório.");
        }
        if (pessoaDTO.getPeso() == 0) {
            errors.add("Peso é um campo obrigatório.");
        }
        if (pessoaDTO.getAltura() == 0) {
            errors.add("Altura é um campo obrigatório.");
        }
        if (pessoaDTO.getSenha() == null || pessoaDTO.getSenha().isEmpty()) {
            errors.add("Senha é um campo obrigatório.");
        }

        if (!errors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        return ResponseEntity.ok().build();
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

