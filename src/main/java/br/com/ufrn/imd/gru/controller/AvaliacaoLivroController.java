package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.AvaliacaoDTO;
import br.com.ufrn.imd.gru.dto.AvaliacaoEventoDTO;
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
    @GetMapping("/editar/{id}")
    public String editarAvaliacaoForm(@PathVariable Long id, Model model) {
        AvaliacaoLivroDTO avaliacao = avaliacaoLivroService.getById(id);
        if (avaliacao != null) {
            model.addAttribute("avaliacao", avaliacao);
            model.addAttribute("livro", avaliacao.getLivro());
            return "editar-avaliacao-livro";
        } else {
            throw new IllegalArgumentException("Avaliação não encontrada");
        }
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarAvaliacao(@PathVariable("id") Long id, @ModelAttribute AvaliacaoLivroDTO avaliacaoDto, Model model) {
        AvaliacaoLivroDTO avaliacao = avaliacaoLivroService.getById(id);

        if (avaliacao != null) {
            avaliacao.setDescricao(avaliacaoDto.getDescricao());
            avaliacao.setTituloResenha(avaliacaoDto.getTituloResenha());
            avaliacao.setAutorResenha(avaliacaoDto.getAutorResenha());
            avaliacaoLivroService.atualizar(id, avaliacao);
            model.addAttribute("successMessage", "Avaliação atualizada com sucesso!");
            return "redirect:/avaliacoes/livro";
        } else {
            model.addAttribute("errorMessage", "Avaliação não encontrada");
            return "editar-avaliacao";
        }
    }
}
