package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.AvaliacaoGRUDTO;
import br.com.ufrn.imd.gru.model.AvaliacaoGRU;
import br.com.ufrn.imd.gru.model.Cardapio;
import br.com.ufrn.imd.gru.service.AvaliacaoGRUService;
import br.com.ufrn.imd.gru.service.CardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/avaliacao")
public class AvaliacaoGRUController extends AvaliacaoController<AvaliacaoGRUDTO> {

    private final AvaliacaoGRUService avaliacaoService;
    private final CardapioService cardapioService;

    @Autowired
    public AvaliacaoGRUController(AvaliacaoGRUService avaliacaoService, CardapioService cardapioService) {
        this.avaliacaoService = avaliacaoService;
        this.cardapioService = cardapioService;
    }

    @GetMapping("/cadastrar")
    public String telaInicialComum(Model model) {
        LocalDate dataAtual = LocalDate.now();
        List<Cardapio> cardapios = cardapioService.getCardapiosAtuais(dataAtual);
        List<AvaliacaoGRU> avaliacoes = avaliacaoService.getAvaliacoesAtuais();
        model.addAttribute("cardapios", cardapios);
        model.addAttribute("avaliacoes", avaliacoes);
        model.addAttribute("novaAvaliacao", new AvaliacaoGRU());
        return "avaliacoes";
    }

    @PostMapping("/cadastrar")
    public String cadastrarAvaliacao(AvaliacaoGRUDTO novaAvaliacao, Model model) {
        LocalDate dataAtual = LocalDate.now();
        Cardapio cardapio = cardapioService.findByCardapio(novaAvaliacao.getTipoCardapio(), novaAvaliacao.getTipoRefeicao(), dataAtual);

        if (cardapio != null) {
            novaAvaliacao.setCardapio(cardapio);
            AvaliacaoGRU avaliacao = new AvaliacaoGRU();
            avaliacao.setQuantidadeEstrelas(novaAvaliacao.getQuantidadeEstrelas());
            avaliacao.setDescricao(novaAvaliacao.getDescricao());
            avaliacao.setCardapio(novaAvaliacao.getCardapio());

            avaliacaoService.cadastrar(avaliacao);
            model.addAttribute("successMessage", "Avaliação cadastrada com sucesso!");
        } else {
            model.addAttribute("errorMessage", "Cardápio não encontrado");
        }

        ResponseEntity<String> response = create(novaAvaliacao);
        model.addAttribute("responseMessage", response.getBody());

        return "avaliacoes";
    }

    @PostMapping("/atualizar")
    public String atualizarAvaliacao(@RequestParam("id_avaliacao") long id, @ModelAttribute AvaliacaoGRUDTO avaliacaoDTO) {
        avaliacaoService.atualizar(id, avaliacaoDTO);
        return "avaliacoes";
    }
    @Override
    @PostMapping("/excluir-avaliacao/{id}")
    public String deleteById(@PathVariable long id) {
        avaliacaoService.deleteById(id);
        return "redirect:/avaliacao/cadastrar";
    }
    @GetMapping("/editar-avaliacao/{id}")
    public String editarAvaliacao(@PathVariable Long id, Model model) {
        AvaliacaoGRU avaliacao = avaliacaoService.getById(id);
        Cardapio cardapio = avaliacao.getCardapio();
        model.addAttribute("avaliacao", avaliacao);
        model.addAttribute("cardapio", cardapio);
        return "editar-avaliacao";
    }

    @Override
    public void update(long id, AvaliacaoGRUDTO avaliacaoDto) {
        // Implementação da atualização de avaliação específica, se necessário
    }

}
