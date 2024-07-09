package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.model.Livro;
import br.com.ufrn.imd.gru.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    private final LivroRepository livroRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> getLivrosAtuais() {
        return livroRepository.findAll();
    }
}
