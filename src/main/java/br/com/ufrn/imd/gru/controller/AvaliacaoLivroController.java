package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.AvaliacaoLivroDTO;
import br.com.ufrn.imd.gru.model.AvaliacaoLivro;
import br.com.ufrn.imd.gru.model.Livro;
import br.com.ufrn.imd.gru.service.AvaliacaoLivroService;
import br.com.ufrn.imd.gru.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/avaliacoes/livro")
public class AvaliacaoLivroController extends AvaliacaoController<AvaliacaoLivroDTO> {
    private final AvaliacaoLivroService avaliacaoLivroService;
    private final LivroService livroService;
    @Autowired
    public AvaliacaoLivroController(AvaliacaoLivroService avaliacaoLivroService, LivroService livroService) {
        super(avaliacaoLivroService);
        this.avaliacaoLivroService = avaliacaoLivroService;
        this.livroService = livroService;
    }
    @PostMapping
    public String cadastrarAvaliacao(AvaliacaoLivroDTO avaliacaoDto, Model model) {
        AvaliacaoLivroDTO avaliacaoLivro = new AvaliacaoLivroDTO();
        avaliacaoLivro.setId(avaliacaoDto.getId());
        avaliacaoLivro.setDescricao(avaliacaoDto.getDescricao());
        avaliacaoLivro.setTituloResenha(avaliacaoDto.getTituloResenha());
        avaliacaoLivro.setAutorResenha(avaliacaoDto.getAutorResenha());
        avaliacaoLivro.setLivro(avaliacaoDto.getLivro());
        avaliacaoLivroService.cadastrar(avaliacaoLivro);
        return "avaliacoes-livros";
    }
    @GetMapping
    public String listarAvaliacoes(Model model) {
        List<Livro> livros = livroService.getLivrosAtuais();
        List<AvaliacaoLivroDTO> avaliacoes = avaliacaoLivroService.buscarAvaliacoesAtuais();
        model.addAttribute("avaliacoes", avaliacoes);
        model.addAttribute("livros", livros);
        return "avaliacoes-livros";
    }
    public void atualizarAvaliacao(long id, AvaliacaoLivroDTO avaliacaoDto) {
        AvaliacaoLivroDTO avaliacaoLivro = avaliacaoLivroService.getById(id);
        if (avaliacaoLivro != null) {
            avaliacaoLivro.setDescricao(avaliacaoDto.getDescricao());
            avaliacaoLivro.setTituloResenha(avaliacaoDto.getTituloResenha());
            avaliacaoLivro.setAutorResenha(avaliacaoDto.getAutorResenha());
            avaliacaoLivro.setLivro(avaliacaoDto.getLivro());
            avaliacaoLivroService.atualizar(id, avaliacaoDto);
        } else {
            throw new IllegalArgumentException("Avaliação de livro não encontrada");
        }
    }
}
