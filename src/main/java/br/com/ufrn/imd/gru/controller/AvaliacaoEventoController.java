package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.AvaliacaoEventoDTO;
import br.com.ufrn.imd.gru.model.AvaliacaoEvento;
import br.com.ufrn.imd.gru.service.AvaliacaoEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacoes/evento")
public class AvaliacaoEventoController extends AvaliacaoController<AvaliacaoEventoDTO> {

    private final AvaliacaoEventoService avaliacaoService;

    @Autowired
    public AvaliacaoEventoController(AvaliacaoEventoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody AvaliacaoEventoDTO avaliacaoEventoDto) {
        // Implementação específica para criar uma avaliação de evento
        AvaliacaoEvento avaliacao = new AvaliacaoEvento();
        avaliacao.setDescricao(avaliacaoEventoDto.getDescricao());
        avaliacao.setEstrelasAcessibilidade(avaliacaoEventoDto.getEstrelasAcessibilidade());
        avaliacao.setEstrelasPontualidade(avaliacaoEventoDto.getEstrelasPontualidade());
        avaliacao.setEstrelasPalestrante(avaliacaoEventoDto.getEstrelasPalestrante());
        avaliacao.setEvento(avaliacaoEventoDto.getEvento());
        avaliacaoService.cadastrar(avaliacao);
        return ResponseEntity.ok("Avaliação de evento cadastrada com sucesso!");
    }

    @Override
    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody AvaliacaoEventoDTO avaliacaoDto) {
        // Implementação específica para atualizar uma avaliação de evento
        AvaliacaoEvento avaliacao = avaliacaoService.getById(id);
        avaliacao.setDescricao(avaliacaoDto.getDescricao());
        avaliacao.setEstrelasAcessibilidade(avaliacaoDto.getEstrelasAcessibilidade());
        avaliacao.setEstrelasPontualidade(avaliacaoDto.getEstrelasPontualidade());
        avaliacao.setEstrelasPalestrante(avaliacaoDto.getEstrelasPalestrante());
        avaliacaoService.atualizar(avaliacao);
    }

    @Override
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable long id) {
        // Implementação específica para excluir uma avaliação de evento
        avaliacaoService.deleteById(id);
        return "Avaliação de evento excluída com sucesso!";
    }
}
