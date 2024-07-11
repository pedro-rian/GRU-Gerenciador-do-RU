package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.PessoaBibliotecaDTO;
import br.com.ufrn.imd.gru.dto.PessoaDTO;
import br.com.ufrn.imd.gru.dto.PessoaEventoDTO;
import br.com.ufrn.imd.gru.dto.PessoaGruDTO;
import br.com.ufrn.imd.gru.model.Pessoa;
import br.com.ufrn.imd.gru.model.PessoaBiblioteca;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaBibliotecaService implements PessoaStrategy{

    UsuarioService usuarioService;

    @Override
    public ResponseEntity<List<String>> validarPessoa(PessoaDTO pessoaDTO) {
        if(pessoaDTO instanceof PessoaBibliotecaDTO) {
            PessoaBibliotecaDTO pessoaBibliotecaDTO = (PessoaBibliotecaDTO) pessoaDTO;

            List<String> errors = new ArrayList<>();
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

    }
}
