package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.AvaliacaoDTO;
import org.springframework.http.ResponseEntity;

public abstract class AvaliacaoController<T extends AvaliacaoDTO> {

    public ResponseEntity<String> create(T avaliacaoDto) {
        // Common logic for creating an evaluation
        return ResponseEntity.ok("successfully");
    }
    public abstract void update(long id, T avaliacaoDto);

    // Método abstrato para deleção de avaliação
    public abstract String deleteById(long id);
}
