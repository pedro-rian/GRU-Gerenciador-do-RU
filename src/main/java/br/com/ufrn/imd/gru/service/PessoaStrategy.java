package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.PessoaDTO;
import br.com.ufrn.imd.gru.dto.PessoaGruDTO;
import br.com.ufrn.imd.gru.model.Pessoa;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PessoaStrategy {
    ResponseEntity<List<String>> validarPessoa(PessoaDTO pessoaDTO);
    boolean existeUsuarioComEmail(String email);
    void salvarDados(Pessoa pessoa);
}
