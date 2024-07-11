package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.PessoaDTO;
import br.com.ufrn.imd.gru.dto.PessoaGruDTO;
import br.com.ufrn.imd.gru.model.Pessoa;
import br.com.ufrn.imd.gru.model.PessoaGRU;
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
public class PessoaGRUService implements PessoaStrategy {
    private final PessoaRepository pessoaRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public PessoaGRUService(PessoaRepository pessoaRepository, UsuarioService usuarioService) {
        this.pessoaRepository = pessoaRepository;
        this.usuarioService = usuarioService;
    }

    public double calcularIMC(PessoaGRU pessoaGRU) {
        return pessoaGRU.getPeso() / (pessoaGRU.getAltura() * pessoaGRU.getAltura());
    }

    @Override
    public ResponseEntity<List<String>> validarPessoa(PessoaDTO pessoaDTO) {
        if(pessoaDTO instanceof PessoaGruDTO) {
            PessoaGruDTO pessoaGRUDTO = (PessoaGruDTO) pessoaDTO;
            List<String> errors = new ArrayList<>();

            if (pessoaGRUDTO.getNome() == null || pessoaGRUDTO.getNome().isEmpty()) {
                errors.add("Nome é um campo obrigatório.");
            }
            if (pessoaGRUDTO.getEmail() == null || pessoaGRUDTO.getEmail().isEmpty()) {
                errors.add("E-mail é um campo obrigatório.");
            }
            if (existeUsuarioComEmail(pessoaGRUDTO.getEmail())) {
                errors.add("Já existe um usuário com esse e-mail.");
            }
            if (pessoaGRUDTO.getIdade() == 0) {
                errors.add("Idade é um campo obrigatório.");
            }
            if (pessoaGRUDTO.getPeso() == 0) {
                errors.add("Peso é um campo obrigatório.");
            }
            if (pessoaGRUDTO.getAltura() == 0) {
                errors.add("Altura é um campo obrigatório.");
            }
            if (pessoaGRUDTO.getSenha() == null || pessoaGRUDTO.getSenha().isEmpty()) {
                errors.add("Senha é um campo obrigatório.");
            }

            if (!errors.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
            }


        }
        return ResponseEntity.ok().build();
    }

    @Override
    public boolean existeUsuarioComEmail(String email) {
        return usuarioService.existeUsuarioComEmail(email);
    }

    @Override
    public void salvarDados(Pessoa pessoa) {
        if (pessoa instanceof PessoaGRU) {
            PessoaGRU pessoaGRU = (PessoaGRU) pessoa;

            if (pessoaGRU.getUsuario() == null) {
                Usuario novoUsuario = new Usuario();
                pessoaGRU.setUsuario(novoUsuario);
            }

            double imc = calcularIMC(pessoaGRU);
            pessoaGRU.setImc(imc);

            pessoaGRU.getUsuario().setAtivo(true);

            Usuario usuario = pessoaGRU.getUsuario();
            if (usuario != null) {
                usuarioService.salvarUsuario(usuario);
            } else {
            }
            pessoaRepository.save(pessoaGRU);

        } else {
            throw new IllegalArgumentException("Tipo de pessoa inválido para GRU");
        }

    }


    public PessoaGRU buscarPorIdDoUsuario(Long idUsuario) {
        Optional<PessoaGRU> optionalPessoa = pessoaRepository.findByUsuarioId(idUsuario);
        return optionalPessoa.orElse(null);
    }


}

