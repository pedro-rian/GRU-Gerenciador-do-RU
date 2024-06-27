package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.AvaliacaoLivroDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacoes/livro")
public class AvaliacaoLivroController extends AvaliacaoController<AvaliacaoLivroDTO> {

    @PostMapping
    public ResponseEntity<String> create(@RequestBody AvaliacaoLivroDTO avaliacaoLivroDto) {
        return super.create(avaliacaoLivroDto);
    }

    @Override
    public void update(long id, AvaliacaoLivroDTO avaliacaoDto) {
        // Implementação específica para atualizar uma avaliação de livro
    }

    @Override
    public String deleteById(long id) {
        // Implementação específica para excluir uma avaliação de livro
        return null;
    }


}
