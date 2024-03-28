package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.service.CardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CardapioController {
    private CardapioService cardapioService;

    @Autowired
    public CardapioController(CardapioService cardapioService) {
        this.cardapioService = cardapioService;
    }
}
