package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.AvaliacaoEventoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacoes/evento")
public class AvaliacaoEventoController extends AvaliacaoController<AvaliacaoEventoDTO> {

    @PostMapping
    public ResponseEntity<String> create(@RequestBody AvaliacaoEventoDTO avaliacaoEventoDto) {
        return super.create(avaliacaoEventoDto);
    }
    @Override
    public void update(long id, AvaliacaoEventoDTO avaliacaoDto) {
        // Implementação específica para atualizar uma avaliação de livro
    }

    @Override
    public String deleteById(long id) {
        // Implementação específica para excluir uma avaliação de livro
        return null;
    }

}