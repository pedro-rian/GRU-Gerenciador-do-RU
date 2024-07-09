package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.AvaliacaoDTO;
import br.com.ufrn.imd.gru.dto.AvaliacaoGRUDTO;
import br.com.ufrn.imd.gru.model.AvaliacaoGRU;
import br.com.ufrn.imd.gru.repository.AvaliacaoRepositoryGRU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoGRUService implements AvaliacaoService<AvaliacaoGRUDTO> {

    @Autowired
    private AvaliacaoRepositoryGRU avaliacaoRepository;

    @Override
    public AvaliacaoGRU cadastrar(AvaliacaoGRUDTO avaliacaoDto) {
        AvaliacaoGRU avaliacao = new AvaliacaoGRU();
        avaliacao.setQuantidadeEstrelas(avaliacaoDto.getQuantidadeEstrelas());
        avaliacao.setDescricao(avaliacaoDto.getDescricao());
        avaliacao.setCardapio(avaliacaoDto.getCardapio());
        return avaliacaoRepository.save(avaliacao);
    }

    @Override
    public void atualizar(long id, AvaliacaoGRUDTO avaliacaoDto) {
        AvaliacaoGRU avaliacao = avaliacaoRepository.findById(id).orElse(null);
        if (avaliacao != null) {
            avaliacao.setQuantidadeEstrelas(avaliacaoDto.getQuantidadeEstrelas());
            avaliacao.setDescricao(avaliacaoDto.getDescricao());
            avaliacaoRepository.save(avaliacao);
        } else {
            throw new IllegalArgumentException("Avaliação não encontrada");
        }
    }

    @Override
    public List<AvaliacaoGRU> getAvaliacoesAtuais() {
        return avaliacaoRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        avaliacaoRepository.deleteById(id);
    }

    @Override
    public AvaliacaoGRUDTO getById(long id) {
        return null;
    }

    public void validarDadosAvaliacao(AvaliacaoGRUDTO avaliacaoDto) {
        int quantidadeEstrelas = (int) avaliacaoDto.getQuantidadeEstrelas();
        if (quantidadeEstrelas < 1 || quantidadeEstrelas > 5) {
            throw new IllegalArgumentException("A quantidade de estrelas deve estar entre 1 e 5");
        }
    }
}
