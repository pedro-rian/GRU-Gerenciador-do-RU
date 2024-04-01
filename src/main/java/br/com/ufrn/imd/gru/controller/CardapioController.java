package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.model.Cardapio;
import br.com.ufrn.imd.gru.service.CardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/cardapio")
public class CardapioController {
    private CardapioService cardapioService;

    @Autowired
    public CardapioController(CardapioService cardapioService) {
        this.cardapioService = cardapioService;
    }

    @GetMapping("tela-inicial-comum")
    public String mostrarCardapio(Model model) {
        LocalDate dataAtual = LocalDate.now();
        List<Cardapio> cardapios = cardapioService.getCardapiosAtuais(dataAtual);
        model.addAttribute("cardapios", cardapios);
        return "tela-inicial-comum";
    }

}
