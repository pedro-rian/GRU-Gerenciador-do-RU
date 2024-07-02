package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.AvaliacaoEventoDTO;
import br.com.ufrn.imd.gru.model.AvaliacaoEvento;
import br.com.ufrn.imd.gru.model.AvaliacaoGRU;
import br.com.ufrn.imd.gru.repository.AvaliacaoEventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoEventoService implements AvaliacaoService<AvaliacaoEventoDTO>{

    private final AvaliacaoEventoRepository avaliacaoEventoRepository;

    @Autowired
    public AvaliacaoEventoService(AvaliacaoEventoRepository avaliacaoEventoRepository) {
        this.avaliacaoEventoRepository = avaliacaoEventoRepository;
    }

    @Override
    public AvaliacaoEventoDTO getById(long id) {
        Optional<AvaliacaoEvento> optionalAvaliacaoEvento = avaliacaoEventoRepository.findById(id);
        return optionalAvaliacaoEvento.map(this::convertToDTO).orElse(null);
    }

    @Override
    public void validarDadosAvaliacao(AvaliacaoEventoDTO avaliacaoDto) {
        if (avaliacaoDto.getDescricao() == null || avaliacaoDto.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("Descrição da avaliação não pode ser vazia");
        }
        // Adicione outras validações conforme necessário
    }

    @Override
    public AvaliacaoGRU cadastrar(AvaliacaoEventoDTO avaliacaoDto) {
        validarDadosAvaliacao(avaliacaoDto); // Valida os dados antes de cadastrar

        AvaliacaoEvento avaliacaoEvento = new AvaliacaoEvento();
        avaliacaoEvento.setDescricao(avaliacaoDto.getDescricao());
        avaliacaoEvento.setEstrelasAcessibilidade(avaliacaoDto.getEstrelasAcessibilidade());
        avaliacaoEvento.setEstrelasPontualidade(avaliacaoDto.getEstrelasPontualidade());
        avaliacaoEvento.setEstrelasPalestrante(avaliacaoDto.getEstrelasPalestrante());
        avaliacaoEvento.setEvento(avaliacaoDto.getEvento());

        avaliacaoEventoRepository.save(avaliacaoEvento);
        return null;
    }

    @Override
    public void atualizar(long id, AvaliacaoEventoDTO avaliacao) {
        Optional<AvaliacaoEvento> optionalAvaliacaoEvento = avaliacaoEventoRepository.findById(id);
        if (optionalAvaliacaoEvento.isPresent()) {
            AvaliacaoEvento avaliacaoEvento = optionalAvaliacaoEvento.get();
            avaliacaoEvento.setDescricao(avaliacao.getDescricao());
            avaliacaoEvento.setEstrelasAcessibilidade(avaliacao.getEstrelasAcessibilidade());
            avaliacaoEvento.setEstrelasPontualidade(avaliacao.getEstrelasPontualidade());
            avaliacaoEvento.setEstrelasPalestrante(avaliacao.getEstrelasPalestrante());
            avaliacaoEvento.setEvento(avaliacao.getEvento());
            avaliacaoEventoRepository.save(avaliacaoEvento);
        } else {
            throw new IllegalArgumentException("Avaliação de evento não encontrada");
        }
    }

    @Override
    public List<AvaliacaoGRU> getAvaliacoesAtuais() {
        return null;
    }

    @Override
    public void deleteById(long id) {
        avaliacaoEventoRepository.deleteById(id);
    }

    private AvaliacaoEventoDTO convertToDTO(AvaliacaoEvento avaliacaoEvento) {
        AvaliacaoEventoDTO dto = new AvaliacaoEventoDTO();
        dto.setId(avaliacaoEvento.getId());
        dto.setDescricao(avaliacaoEvento.getDescricao());
        dto.setEstrelasAcessibilidade(avaliacaoEvento.getEstrelasAcessibilidade());
        dto.setEstrelasPontualidade(avaliacaoEvento.getEstrelasPontualidade());
        dto.setEstrelasPalestrante(avaliacaoEvento.getEstrelasPalestrante());
        dto.setEvento(avaliacaoEvento.getEvento());
        return dto;
    }

}
