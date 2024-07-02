package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.AvaliacaoGRUDTO;
import br.com.ufrn.imd.gru.model.AvaliacaoGRU;
import br.com.ufrn.imd.gru.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoGRUService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoGRU cadastrar(AvaliacaoGRU avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    public void atualizar(long id, AvaliacaoGRUDTO avaliacaoDTO) {
        AvaliacaoGRU avaliacao = avaliacaoRepository.findById(id).get();
        avaliacao.setQuantidadeEstrelas(avaliacaoDTO.getQuantidadeEstrelas());
        avaliacao.setDescricao(avaliacaoDTO.getDescricao());
        avaliacaoRepository.save(avaliacao);
    }

    public List<AvaliacaoGRU> getAvaliacoesAtuais() {
        return avaliacaoRepository.findAll();
    }

    public void deleteById(long id) {
        avaliacaoRepository.deleteById(id);
    }

    public AvaliacaoGRU getById(long id) {
        return avaliacaoRepository.findById(id).orElse(null);
    }

}
