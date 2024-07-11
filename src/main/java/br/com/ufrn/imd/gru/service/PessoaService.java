package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.PessoaDTO;
import br.com.ufrn.imd.gru.dto.PessoaGruDTO;
import br.com.ufrn.imd.gru.model.Pessoa;
import br.com.ufrn.imd.gru.model.PessoaGRU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private PessoaStrategy pessoaStrategy;

    @Autowired
    private PessoaGRUService pessoaGRUService;

    @Autowired
    private PessoaBibliotecaService pessoaBibliotecaService;

    @Autowired
    private PessoaEventoService pessoaEventoService;

    public void setEstrategia(String tipoSistema) {
        switch (tipoSistema) {
            case "GRU":
                this.pessoaStrategy = pessoaGRUService;
                break;
            case "BIB":
                this.pessoaStrategy = pessoaBibliotecaService;
                break;
            case "EVE":
                this.pessoaStrategy = pessoaEventoService;
                break;
            default:
                throw new IllegalArgumentException("Tipo de sistema desconhecido: " + tipoSistema);
        }
    }

    public ResponseEntity<List<String>> validarPessoa(PessoaDTO pessoaDTO) {
        return pessoaStrategy.validarPessoa(pessoaDTO);
    }

    public boolean existeUsuarioComEmail(String email) {
        return pessoaStrategy.existeUsuarioComEmail(email);
    }

    public void salvarDados(Pessoa pessoa) {
        pessoaStrategy.salvarDados(pessoa);
    }
}
