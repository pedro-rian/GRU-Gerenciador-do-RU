package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.AvaliacaoEventoDTO;
import br.com.ufrn.imd.gru.model.AvaliacaoEvento;
import br.com.ufrn.imd.gru.repository.AvaliacaoEventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliacaoEventoService extends AbstractAvaliacaoService<AvaliacaoEventoDTO> {

    private final AvaliacaoEventoRepository avaliacaoEventoRepository;

    @Autowired
    public AvaliacaoEventoService(AvaliacaoEventoRepository avaliacaoEventoRepository) {
        this.avaliacaoEventoRepository = avaliacaoEventoRepository;
    }

    protected void salvar(AvaliacaoEventoDTO avaliacaoDto) {
        AvaliacaoEvento avaliacao = new AvaliacaoEvento();
        avaliacao.setDescricao(avaliacaoDto.getDescricao());
        avaliacao.setEstrelasAcessibilidade(avaliacaoDto.getEstrelasAcessibilidade());
        avaliacao.setEstrelasPontualidade(avaliacaoDto.getEstrelasPontualidade());
        avaliacao.setEstrelasPalestrante(avaliacaoDto.getEstrelasPalestrante());
        avaliacao.setEvento(avaliacaoDto.getEvento());
        avaliacaoEventoRepository.save(avaliacao);
    }

    @Override
    protected void atualizarDados(long id, AvaliacaoEventoDTO avaliacaoDto) {
        AvaliacaoEvento avaliacao = avaliacaoEventoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Avaliação não encontrada"));
        avaliacao.setDescricao(avaliacaoDto.getDescricao());
        avaliacao.setEstrelasAcessibilidade(avaliacaoDto.getEstrelasAcessibilidade());
        avaliacao.setEstrelasPontualidade(avaliacaoDto.getEstrelasPontualidade());
        avaliacao.setEstrelasPalestrante(avaliacaoDto.getEstrelasPalestrante());
        avaliacao.setEvento(avaliacaoDto.getEvento());
        avaliacaoEventoRepository.save(avaliacao);
    }


    public List<AvaliacaoEventoDTO> buscarAvaliacoesAtuais() {
        return avaliacaoEventoRepository.findAll().stream()
                .map(avaliacao -> {
                    AvaliacaoEventoDTO dto = new AvaliacaoEventoDTO();
                    dto.setId(avaliacao.getId());
                    dto.setDescricao(avaliacao.getDescricao());
                    dto.setEstrelasAcessibilidade(avaliacao.getEstrelasAcessibilidade());
                    dto.setEstrelasPontualidade(avaliacao.getEstrelasPontualidade());
                    dto.setEstrelasPalestrante(avaliacao.getEstrelasPalestrante());
                    dto.setEvento(avaliacao.getEvento());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    protected void excluirPorId(long id) {
        avaliacaoEventoRepository.deleteById(id);
    }

    @Override
    public AvaliacaoEventoDTO getById(long id) {
        return avaliacaoEventoRepository.findById(id)
                .map(avaliacao -> {
                    AvaliacaoEventoDTO dto = new AvaliacaoEventoDTO();
                    dto.setId(avaliacao.getId());
                    dto.setDescricao(avaliacao.getDescricao());
                    dto.setEstrelasAcessibilidade(avaliacao.getEstrelasAcessibilidade());
                    dto.setEstrelasPontualidade(avaliacao.getEstrelasPontualidade());
                    dto.setEstrelasPalestrante(avaliacao.getEstrelasPalestrante());
                    dto.setEvento(avaliacao.getEvento());
                    return dto;
                })
                .orElseThrow(() -> new IllegalArgumentException("Avaliação não encontrada"));
    }

    @Override
    public void validarDadosAvaliacao(AvaliacaoEventoDTO avaliacaoDto) {
        super.validarDadosAvaliacao(avaliacaoDto);

        if (avaliacaoDto.getEstrelasAcessibilidade() < 1 || avaliacaoDto.getEstrelasAcessibilidade() > 5) {
            throw new IllegalArgumentException("A quantidade de estrelas deve estar entre 1 e 5");
        }
        if (avaliacaoDto.getEstrelasPontualidade() < 1 || avaliacaoDto.getEstrelasPontualidade() > 5) {
            throw new IllegalArgumentException("A quantidade de estrelas deve estar entre 1 e 5");
        }
        if (avaliacaoDto.getEstrelasPalestrante() < 1 || avaliacaoDto.getEstrelasPalestrante() > 5) {
            throw new IllegalArgumentException("A quantidade de estrelas deve estar entre 1 e 5");
        }
    }

}
