package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.AvaliacaoDTO;
import br.com.ufrn.imd.gru.dto.AvaliacaoLivroDTO;
import br.com.ufrn.imd.gru.model.AvaliacaoGRU;
import br.com.ufrn.imd.gru.model.AvaliacaoLivro;
import br.com.ufrn.imd.gru.repository.AvaliacaoLivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoLivroService implements AvaliacaoService<AvaliacaoLivroDTO> {

    private final AvaliacaoLivroRepository avaliacaoLivroRepository;

    @Autowired
    public AvaliacaoLivroService(AvaliacaoLivroRepository avaliacaoLivroRepository) {
        this.avaliacaoLivroRepository = avaliacaoLivroRepository;
    }

    @Override
    public AvaliacaoLivroDTO getById(long id) {
        return null;
    }

    @Override
    public void validarDadosAvaliacao(AvaliacaoLivroDTO avaliacaoDto) {

    }

    @Override
    public AvaliacaoGRU cadastrar(AvaliacaoLivroDTO avaliacaoDto) {
        AvaliacaoLivro avaliacaoLivro = new AvaliacaoLivro();
        avaliacaoLivro.setDescricao(avaliacaoDto.getDescricao());
        avaliacaoLivro.setTituloResenha(avaliacaoDto.getTituloResenha());
        avaliacaoLivro.setAutorResenha(avaliacaoDto.getAutorResenha());
        avaliacaoLivroRepository.save(avaliacaoLivro);
        return null;
    }

    @Override
    public void atualizar(long id, AvaliacaoLivroDTO avaliacaoDto) {
        Optional<AvaliacaoLivro> optionalAvaliacaoLivro = avaliacaoLivroRepository.findById(id);
        if (optionalAvaliacaoLivro.isPresent()) {
            AvaliacaoLivro avaliacaoLivro = optionalAvaliacaoLivro.get();
            avaliacaoLivro.setDescricao(avaliacaoDto.getDescricao());
            avaliacaoLivro.setTituloResenha(avaliacaoDto.getTituloResenha());
            avaliacaoLivro.setAutorResenha(avaliacaoDto.getAutorResenha());
            avaliacaoLivroRepository.save(avaliacaoLivro);
        } else {
            throw new IllegalArgumentException("Avaliação de livro não encontrada");
        }
    }

    @Override
    public List<AvaliacaoGRU> getAvaliacoesAtuais() {
        return null;
    }


    @Override
    public void deleteById(long id) {
        avaliacaoLivroRepository.deleteById(id);
    }


    public List<AvaliacaoLivro> getAll() {
        return avaliacaoLivroRepository.findAll();
    }

    public void atualizar(AvaliacaoLivroDTO avaliacaoLivro) {
    }
}
