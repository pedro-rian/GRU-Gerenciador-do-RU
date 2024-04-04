package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.model.Avaliacao;
import br.com.ufrn.imd.gru.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public void cadastrarAvaliacao(String nome, String email, String descricao) throws Exception {
        validacaoAvaliacao(nome, email, descricao);
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNome(nome);
        avaliacao.setDescricao(descricao);
        avaliacao.setEmail(email);

        avaliacaoRepository.save(avaliacao);
    }

    public void validacaoAvaliacao(String nome, String email, String descricao) throws Exception {
        if(nome.isEmpty() || email.isEmpty() || descricao.isEmpty() || (nome.length() < 0 && nome.length() > 200) || (email.length() < 0 && email.length() > 50) || (descricao.length() < 0 && descricao.length() > 500)) {
            throw new Exception("Erro na validação dos campos");
        }
    }
}
