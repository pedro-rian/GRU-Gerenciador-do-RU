package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.AvaliacaoLivroDTO;
import br.com.ufrn.imd.gru.model.AvaliacaoLivro;
import br.com.ufrn.imd.gru.service.AvaliacaoLivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacoes/livro")
public class AvaliacaoLivroController extends AvaliacaoController<AvaliacaoLivroDTO> {

    private final AvaliacaoLivroService avaliacaoLivroService;

    @Autowired
    public AvaliacaoLivroController(AvaliacaoLivroService avaliacaoLivroService) {
        this.avaliacaoLivroService = avaliacaoLivroService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody AvaliacaoLivroDTO avaliacaoLivroDto) {
        // Implementação específica para criar uma avaliação de livro
        AvaliacaoLivro avaliacaoLivro = new AvaliacaoLivro();
        avaliacaoLivro.setDescricao(avaliacaoLivroDto.getDescricao());
        avaliacaoLivro.setTituloResenha(avaliacaoLivroDto.getTituloResenha());
        avaliacaoLivro.setAutorResenha(avaliacaoLivroDto.getAutorResenha());
        avaliacaoLivro.setLivro(avaliacaoLivroDto.getLivro());
        avaliacaoLivroService.cadastrar(avaliacaoLivro);
        return ResponseEntity.ok("Avaliação de livro cadastrada com sucesso!");
    }

    @Override
    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody AvaliacaoLivroDTO avaliacaoDto) {
        // Implementação específica para atualizar uma avaliação de livro
        AvaliacaoLivro avaliacaoLivro = avaliacaoLivroService.getById(id);
        avaliacaoLivro.setDescricao(avaliacaoDto.getDescricao());
        avaliacaoLivro.setTituloResenha(avaliacaoDto.getTituloResenha());
        avaliacaoLivro.setAutorResenha(avaliacaoDto.getAutorResenha());
        avaliacaoLivro.setLivro(avaliacaoDto.getLivro());
        avaliacaoLivroService.atualizar(avaliacaoLivro);
    }

    @Override
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable long id) {
        // Implementação específica para excluir uma avaliação de livro
        avaliacaoLivroService.deleteById(id);
        return "Avaliação de livro excluída com sucesso!";
    }
}
