package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.AvaliacaoDTO;
import br.com.ufrn.imd.gru.model.Avaliacao;
import br.com.ufrn.imd.gru.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public Avaliacao cadastrar(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    public void atualizar(long id, AvaliacaoDTO avaliacaoDTO) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id).get();
        avaliacao.setQuantidadeEstrelas(avaliacaoDTO.getQuantidadeEstrelas());
        avaliacao.setDescricao(avaliacaoDTO.getDescricao());
        avaliacaoRepository.save(avaliacao);
    }

    public List<Avaliacao> getAvaliacoesAtuais() {
        return avaliacaoRepository.findAll();
    }

    public void deleteById(long id) {
        avaliacaoRepository.deleteById(id);
    }

    public Avaliacao getById(long id) {
        return avaliacaoRepository.findById(id).orElse(null);
    }

}
