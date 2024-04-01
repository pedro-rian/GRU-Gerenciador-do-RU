package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.model.Aviso;
import br.com.ufrn.imd.gru.model.TipoCardapio;
import br.com.ufrn.imd.gru.model.TipoRefeicao;
import br.com.ufrn.imd.gru.service.AvisoService;
import br.com.ufrn.imd.gru.model.Cardapio;
import br.com.ufrn.imd.gru.service.CardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
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
    public String telaInicialComum(Model model) {
        LocalDate dataAtual = LocalDate.now();
        Aviso aviso = avisoService.getAvisoDoDia(dataAtual);
        model.addAttribute("aviso", aviso);
        List<Cardapio> cardapios = cardapioService.getCardapiosAtuais(dataAtual);
        model.addAttribute("cardapios", cardapios);
        return "tela-inicial-comum";
    }

    @GetMapping("/cadastrar-cardapio-nutricionista")
    public String cadastrarCardapioNutricionista(){return "cadastrar-cardapio-nutricionista";}

    @PostMapping("/cadastrar-cardapio-nutricionista/cadastrar-cardapio")
    public String cadastrarCardapio(@RequestParam("tipo_cardapio") String tipoCardapioString,
                                                 @RequestParam("tipo_refeicao") String tipoRefeicaoString,
                                                 @RequestParam("prato_principal") String pratoPrincipal,
                                                 @RequestParam("acompanhamento") String acompanhamento,
                                                 @RequestParam("data") @DateTimeFormat(pattern = "yyyy-MM-dd") Date data){
        //Lidando com Enums:
        TipoCardapio tipoCardapio = TipoCardapio.valueOf(tipoCardapioString);
        TipoRefeicao tipoRefeicao = TipoRefeicao.valueOf(tipoRefeicaoString);

        Cardapio cardapio = new Cardapio();
        cardapio.setTipoCardapio(tipoCardapio);
        cardapio.setTipoRefeicao(tipoRefeicao);
        cardapio.setPratoPrincipal(pratoPrincipal);
        cardapio.setAcompanhamento(acompanhamento);
        cardapio.setData(data);

        cardapioService.salvarCardapio(cardapio);

        return "redirect:/usuario/tela-inicial-nutricionista";
    }

}
