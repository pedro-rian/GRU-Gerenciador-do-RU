package br.com.ufrn.imd.gru.service;
import br.com.ufrn.imd.gru.model.Pessoa;
import br.com.ufrn.imd.gru.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa autenticarUsuario(String email, String senha) {
        return pessoaRepository.autenticar(email, senha);
    }

}
