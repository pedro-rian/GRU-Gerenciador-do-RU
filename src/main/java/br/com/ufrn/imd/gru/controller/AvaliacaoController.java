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
    @PostMapping
    public ResponseEntity<String> create(@RequestBody T avaliacaoDto) {
        avaliacaoService.validarDadosAvaliacao(avaliacaoDto);
        cadastrarAvaliacao(avaliacaoDto, null);
        return ResponseEntity.ok("Avaliação cadastrada com sucesso!");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable long id, @RequestBody T avaliacaoDto) {
        avaliacaoService.validarDadosAvaliacao(avaliacaoDto);
        atualizarAvaliacao(id, avaliacaoDto);
        return ResponseEntity.ok("Avaliação atualizada com sucesso!");
    }
    @PostMapping("/excluir-avaliacao/{id}")
    public String deleteById(@PathVariable long id) {
        avaliacaoService.deleteById(id);
        return "redirect:/avaliacao/cadastrar";
    }
    protected abstract String cadastrarAvaliacao(T avaliacaoDto, Model model);
    protected abstract void atualizarAvaliacao(long id, T avaliacaoDto);

}
