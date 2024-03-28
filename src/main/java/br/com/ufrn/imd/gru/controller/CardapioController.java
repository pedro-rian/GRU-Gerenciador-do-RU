package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.service.CardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class CardapioController {
    private CardapioService cardapioService;

    @Autowired
    public CardapioController(CardapioService cardapioService) {
        this.cardapioService = cardapioService;
    }



}
