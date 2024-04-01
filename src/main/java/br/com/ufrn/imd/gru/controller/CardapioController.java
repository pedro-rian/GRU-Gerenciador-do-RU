package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.model.Aviso;
import br.com.ufrn.imd.gru.service.AvisoService;
import br.com.ufrn.imd.gru.model.Cardapio;
import br.com.ufrn.imd.gru.service.CardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;

import java.time.LocalDate;


@Controller
@RequestMapping("/cardapio")
public class CardapioController {
    private CardapioService cardapioService;

    private AvisoService avisoService;

    @Autowired
    public CardapioController(CardapioService cardapioService, AvisoService avisoService) {
        this.cardapioService = cardapioService;
        this.avisoService = avisoService;
    }

    @GetMapping("/tela-inicial-comum")
    public String avisos(Model model) {
        LocalDate dataAtual = LocalDate.now();
        Aviso aviso = avisoService.getAvisoDoDia(dataAtual);
        model.addAttribute("aviso", aviso);
        List<Cardapio> cardapios = cardapioService.getCardapiosAtuais(dataAtual);
        model.addAttribute("cardapios", cardapios);
        return "tela-inicial-comum";
    }
    /*@GetMapping("/tela-inicial-comum")
    public String mostrarCardapio(Model model) {
        LocalDate dataAtual = LocalDate.now();

        return "tela-inicial-comum";
    }*/

}
