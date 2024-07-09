package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.AvaliacaoDTO;
import br.com.ufrn.imd.gru.service.AvaliacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

public abstract class AvaliacaoController<T extends AvaliacaoDTO> {
    private final AvaliacaoService<T> avaliacaoService;
    public AvaliacaoController(AvaliacaoService<T> avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping("/excluir-avaliacao/{id}")
    public String deleteById(@PathVariable long id) {
        avaliacaoService.deleteById(id);
        return "redirect:/avaliacao/cadastrar";
    }

}
