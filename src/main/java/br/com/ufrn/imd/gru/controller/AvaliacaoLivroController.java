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
        super(avaliacaoLivroService);
        this.avaliacaoLivroService = avaliacaoLivroService;
    }

    @Override
    protected void cadastrarAvaliacao(AvaliacaoLivroDTO avaliacaoDto) {
        AvaliacaoLivroDTO avaliacaoLivro = new AvaliacaoLivroDTO();
        avaliacaoLivro.setDescricao(avaliacaoDto.getDescricao());
        avaliacaoLivro.setTituloResenha(avaliacaoDto.getTituloResenha());
        avaliacaoLivro.setAutorResenha(avaliacaoDto.getAutorResenha());
        avaliacaoLivro.setLivro(avaliacaoDto.getLivro());
        avaliacaoLivroService.cadastrar(avaliacaoLivro);
    }

    @Override
    protected void atualizarAvaliacao(long id, AvaliacaoLivroDTO avaliacaoDto) {
        AvaliacaoLivroDTO avaliacaoLivro = avaliacaoLivroService.getById(id);
        if (avaliacaoLivro != null) {
            avaliacaoLivro.setDescricao(avaliacaoDto.getDescricao());
            avaliacaoLivro.setTituloResenha(avaliacaoDto.getTituloResenha());
            avaliacaoLivro.setAutorResenha(avaliacaoDto.getAutorResenha());
            avaliacaoLivro.setLivro(avaliacaoDto.getLivro());
            avaliacaoLivroService.atualizar(avaliacaoLivro);
        } else {
            throw new IllegalArgumentException("Avaliação de livro não encontrada");
        }
    }
}
