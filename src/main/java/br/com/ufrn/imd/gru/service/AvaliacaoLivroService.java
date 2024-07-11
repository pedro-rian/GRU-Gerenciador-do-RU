package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.AvaliacaoLivroDTO;
import br.com.ufrn.imd.gru.model.AvaliacaoLivro;
import br.com.ufrn.imd.gru.repository.AvaliacaoLivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliacaoLivroService extends AbstractAvaliacaoService<AvaliacaoLivroDTO> {

    private final AvaliacaoLivroRepository avaliacaoLivroRepository;

    @Autowired
    public AvaliacaoLivroService(AvaliacaoLivroRepository avaliacaoLivroRepository) {
        this.avaliacaoLivroRepository = avaliacaoLivroRepository;
    }

    @Override
    protected void salvar(AvaliacaoLivroDTO avaliacaoDto) {
        AvaliacaoLivro avaliacao = new AvaliacaoLivro();
        avaliacao.setDescricao(avaliacaoDto.getDescricao());
        avaliacao.setTituloResenha(avaliacaoDto.getTituloResenha());
        avaliacao.setAutorResenha(avaliacaoDto.getAutorResenha());
        avaliacaoLivroRepository.save(avaliacao);
    }

    @Override
    protected void atualizarDados(long id, AvaliacaoLivroDTO avaliacaoDto) {
        AvaliacaoLivro avaliacao = avaliacaoLivroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Avaliação não encontrada"));
        avaliacao.setDescricao(avaliacaoDto.getDescricao());
        avaliacao.setTituloResenha(avaliacaoDto.getTituloResenha());
        avaliacao.setAutorResenha(avaliacaoDto.getAutorResenha());
        avaliacaoLivroRepository.save(avaliacao);
    }

    @Override
    public List<AvaliacaoLivroDTO> buscarAvaliacoesAtuais() {
        return avaliacaoLivroRepository.findAll().stream()
                .map(avaliacao -> {
                    AvaliacaoLivroDTO dto = new AvaliacaoLivroDTO();
                    dto.setId(avaliacao.getId());
                    dto.setDescricao(avaliacao.getDescricao());
                    dto.setTituloResenha(avaliacao.getTituloResenha());
                    dto.setAutorResenha(avaliacao.getAutorResenha());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void excluirPorId(long id) {
        avaliacaoLivroRepository.deleteById(id);
    }

    @Override
    public AvaliacaoLivroDTO getById(long id) {
        return avaliacaoLivroRepository.findById(id)
                .map(avaliacao -> {
                    AvaliacaoLivroDTO dto = new AvaliacaoLivroDTO();
                    dto.setId(avaliacao.getId());
                    dto.setDescricao(avaliacao.getDescricao());
                    dto.setTituloResenha(avaliacao.getTituloResenha());
                    dto.setAutorResenha(avaliacao.getAutorResenha());
                    return dto;
                })
                .orElseThrow(() -> new IllegalArgumentException("Avaliação não encontrada"));
    }

    @Override
    public void validarDadosAvaliacao(AvaliacaoLivroDTO avaliacaoDto) {
        super.validarDadosAvaliacao(avaliacaoDto);
        if (avaliacaoDto.getTituloResenha() == null || avaliacaoDto.getTituloResenha().isEmpty()) {
            throw new IllegalArgumentException("Título da resenha não pode ser nulo ou vazio");
        } else if (avaliacaoDto.getAutorResenha() == null || avaliacaoDto.getAutorResenha().isEmpty()) {
            throw new IllegalArgumentException("Autor da resenha não pode ser nulo ou vazio");
        }
    }

}
