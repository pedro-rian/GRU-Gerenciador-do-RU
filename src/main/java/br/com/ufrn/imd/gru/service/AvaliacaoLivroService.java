package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.model.AvaliacaoLivro;
import br.com.ufrn.imd.gru.repository.AvaliacaoLivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoLivroService {

    private final AvaliacaoLivroRepository avaliacaoLivroRepository;

    @Autowired
    public AvaliacaoLivroService(AvaliacaoLivroRepository avaliacaoLivroRepository) {
        this.avaliacaoLivroRepository = avaliacaoLivroRepository;
    }

    public void cadastrar(AvaliacaoLivro avaliacaoLivro) {
        avaliacaoLivroRepository.save(avaliacaoLivro);
    }

    public void atualizar(AvaliacaoLivro avaliacaoLivro) {
        avaliacaoLivroRepository.save(avaliacaoLivro);
    }

    public void deleteById(long id) {
        avaliacaoLivroRepository.deleteById(id);
    }

    public AvaliacaoLivro getById(long id) {
        Optional<AvaliacaoLivro> optionalAvaliacaoLivro = avaliacaoLivroRepository.findById(id);
        return optionalAvaliacaoLivro.orElse(null);
    }

    public List<AvaliacaoLivro> getAll() {
        return avaliacaoLivroRepository.findAll();
    }
}
