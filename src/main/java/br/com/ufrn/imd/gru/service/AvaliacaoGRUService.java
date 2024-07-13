package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.AvaliacaoDTO;
import br.com.ufrn.imd.gru.dto.AvaliacaoGRUDTO;
import br.com.ufrn.imd.gru.model.AvaliacaoGRU;
import br.com.ufrn.imd.gru.repository.AvaliacaoRepositoryGRU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliacaoGRUService extends AbstractAvaliacaoService<AvaliacaoGRUDTO> {

    private final AvaliacaoRepositoryGRU avaliacaoRepository;

    @Autowired
    public AvaliacaoGRUService(AvaliacaoRepositoryGRU avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @Override
    protected void salvar(AvaliacaoGRUDTO avaliacaoDto) {
        AvaliacaoGRU avaliacao = new AvaliacaoGRU();
        avaliacao.setQuantidadeEstrelas(avaliacaoDto.getQuantidadeEstrelas());
        avaliacao.setDescricao(avaliacaoDto.getDescricao());
        avaliacao.setCardapio(avaliacaoDto.getCardapio());
        avaliacaoRepository.save(avaliacao);
    }

    @Override
    protected void atualizarDados(long id, AvaliacaoGRUDTO avaliacaoDto) {
        AvaliacaoGRU avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Avaliação não encontrada"));
        avaliacao.setQuantidadeEstrelas(avaliacaoDto.getQuantidadeEstrelas());
        avaliacao.setDescricao(avaliacaoDto.getDescricao());
        avaliacao.setCardapio(avaliacaoDto.getCardapio());
        avaliacaoRepository.save(avaliacao);
    }

    @Override
    public List<AvaliacaoGRUDTO> buscarAvaliacoesAtuais() {
        return avaliacaoRepository.findAll().stream()
                .map(avaliacao -> {
                    AvaliacaoGRUDTO dto = new AvaliacaoGRUDTO();
                    dto.setId(avaliacao.getId());
                    dto.setQuantidadeEstrelas(avaliacao.getQuantidadeEstrelas());
                    dto.setDescricao(avaliacao.getDescricao());
                    dto.setCardapio(avaliacao.getCardapio());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    @Override
    protected void excluirPorId(long id) {
        avaliacaoRepository.deleteById(id);
    }

    @Override
    public AvaliacaoGRUDTO getById(long id) {
        AvaliacaoGRU avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Avaliação não encontrada"));
        AvaliacaoGRUDTO dto = new AvaliacaoGRUDTO();
        dto.setId(avaliacao.getId());
        dto.setQuantidadeEstrelas(avaliacao.getQuantidadeEstrelas());
        dto.setTipoCardapio(avaliacao.getCardapio().getTipoCardapio());
        dto.setTipoRefeicao(avaliacao.getCardapio().getTipoRefeicao());
        dto.setDate(avaliacao.getCardapio().getData());
        dto.setDescricao(avaliacao.getDescricao());
        dto.setCardapio(avaliacao.getCardapio());
        return dto;
    }

    @Override
    public void validarDadosAvaliacao(AvaliacaoGRUDTO avaliacaoDto) {
        /*int quantidadeEstrelas = avaliacaoDto.getQuantidadeEstrelas();
        if (quantidadeEstrelas < 1 || quantidadeEstrelas > 5) {
            throw new IllegalArgumentException("A quantidade de estrelas deve estar entre 1 e 5");
        }*/
    }
}