package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.AvaliacaoDTO;
import br.com.ufrn.imd.gru.model.Avaliacao;
import br.com.ufrn.imd.gru.model.Cardapio;
import br.com.ufrn.imd.gru.service.AvaliacaoService;
import br.com.ufrn.imd.gru.service.CardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    private AvaliacaoService avaliacaoService;
    private CardapioService cardapioService;
    @Autowired
    public AvaliacaoController(AvaliacaoService avaliacaoService, CardapioService cardapioService) {
        this.avaliacaoService = avaliacaoService;
        this.cardapioService = cardapioService;
    }


    @GetMapping("/cadastrar")
    public String telaInicialComum(Model model) {
        LocalDate dataAtual = LocalDate.now();
        List<Cardapio> cardapios = cardapioService.getCardapiosAtuais(dataAtual);
        List<Avaliacao> avaliacoes = avaliacaoService.getAvaliacoesAtuais();
        model.addAttribute("cardapios", cardapios);
        model.addAttribute("avaliacoes", avaliacoes);
        model.addAttribute("novaAvaliacao", new Avaliacao());
        return "avaliacoes";
    }

    @GetMapping("/visualizar")
    public String telaAvaliacaoNutricionista(Model model) {
        LocalDate dataAtual = LocalDate.now();
        List<Cardapio> cardapios = cardapioService.getCardapiosAtuais(dataAtual);
        List<Avaliacao> avaliacoes = avaliacaoService.getAvaliacoesAtuais();
        model.addAttribute("cardapios", cardapios);
        model.addAttribute("avaliacoes", avaliacoes);
        model.addAttribute("novaAvaliacao", new Avaliacao());
        return "avaliacoes-nutricionista";
    }

    @PostMapping("/cadastrar")
    public String cadastrarAvaliacao(AvaliacaoDTO novaAvaliacao, Model model) {
        LocalDate dataAtual = LocalDate.now();

        Cardapio cardapio = cardapioService.findByCardapio(novaAvaliacao.getTipoCardapio(), novaAvaliacao.getTipoRefeicao(), dataAtual);

        if (cardapio != null) {
            novaAvaliacao.setCardapio(cardapio);
            Avaliacao avaliacao = new Avaliacao();
            avaliacao.setQuantidadeEstrelas(novaAvaliacao.getQuantidadeEstrelas());
            avaliacao.setDescricao(novaAvaliacao.getDescricao());
            avaliacao.setCardapio(novaAvaliacao.getCardapio());

            avaliacaoService.cadastrar(avaliacao);
            model.addAttribute("successMessage", "Avaliação cadastrada com sucesso!");
        } else {
            model.addAttribute("errorMessage", "Cardápio não encontrado");
        }

        return "avaliacoes";
    }


}
