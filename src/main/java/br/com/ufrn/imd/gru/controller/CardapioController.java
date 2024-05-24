package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.CardapioDTO;
import br.com.ufrn.imd.gru.model.Aviso;
import br.com.ufrn.imd.gru.model.TipoCardapio;
import br.com.ufrn.imd.gru.model.TipoRefeicao;
import br.com.ufrn.imd.gru.repository.CardapioRepository;
import br.com.ufrn.imd.gru.service.AvisoService;
import br.com.ufrn.imd.gru.model.Cardapio;
import br.com.ufrn.imd.gru.service.CardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;



@Controller
@RequestMapping("/cardapio")
public class CardapioController {
    private final CardapioRepository cardapioRepository;
    private CardapioService cardapioService;

    private AvisoService avisoService;

    @Autowired
    public CardapioController(CardapioService cardapioService, AvisoService avisoService, CardapioRepository cardapioRepository) {
        this.cardapioService = cardapioService;
        this.avisoService = avisoService;
        this.cardapioRepository = cardapioRepository;
    }

    @GetMapping("/tela-inicial-comum")
    public String telaInicialComum(Model model) {
        LocalDate dataAtual = LocalDate.now();
        Aviso aviso = avisoService.getAvisoDoDia(dataAtual);
        model.addAttribute("aviso", aviso);
        List<Cardapio> cardapiosAtuais = cardapioService.getCardapiosAtuais(dataAtual);
        List<Cardapio> cardapiosConvencionais = cardapioService.getCardapiosConvencionais(dataAtual);
        List<Cardapio> cardapiosVegetarianos = cardapioService.getCardapiosVegetarianos(dataAtual);
        model.addAttribute("cardapiosAtuais", cardapiosAtuais);
        model.addAttribute("cardapiosConvencionais", cardapiosConvencionais);
        model.addAttribute("cardapiosVegetarianos", cardapiosVegetarianos);
        return "tela-inicial-comum";
    }

    @GetMapping("/cardapio-nutricionista")
    public String cardapiosCadastrados(Model model) {
        LocalDate dataAtual = LocalDate.now();
        List<Cardapio> cardapiosAtuais = cardapioService.getCardapiosAtuais(dataAtual);
        List<Cardapio> cardapiosConvencionais = cardapioService.getCardapiosConvencionais(dataAtual);
        List<Cardapio> cardapiosVegetarianos = cardapioService.getCardapiosVegetarianos(dataAtual);
        model.addAttribute("cardapiosAtuais", cardapiosAtuais);
        model.addAttribute("cardapiosConvencionais", cardapiosConvencionais);
        model.addAttribute("cardapiosVegetarianos", cardapiosVegetarianos);
        return "cardapio-nutricionista";
    }

    @GetMapping("/cadastrar-cardapio-nutricionista")
    public String cadastrarCardapioNutricionista(Model model){
        List<Cardapio> cardapios = cardapioService.getCardapios();
        model.addAttribute("cardapios", cardapios);
        return "cadastrar-cardapio-nutricionista";
    }

    @GetMapping("/editar-cardapio/{id}")
    public String editarCardapio(@PathVariable Long id, Model model){
        Cardapio cardapio = cardapioService.findById(id);
        model.addAttribute("cardapio", cardapio);
        return "editar-cardapio";
    }

    @PostMapping("/cadastrar-cardapio-nutricionista/cadastrar-cardapio")
    public String cadastrarCardapio(@ModelAttribute CardapioDTO cardapioDTO, Model model) {
        try {
            cardapioService.cadastrarCardapio(cardapioDTO);
            return "redirect:/usuario/tela-inicial-nutricionista";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "cadastrar-cardapio-nutricionista";
        }
    }

    @PostMapping("/excluir-cardapio/{id}")
    public String excluirCardapio(@PathVariable Long id){
        cardapioService.deleteById(id);
        return "redirect:/cardapio/cardapio-nutricionista";
    }

    @PostMapping("/atualizar-cardapio")
    public String atualizarCardapio(@RequestParam("id_cardapio") long idCardapio, @ModelAttribute CardapioDTO cardapioDTO, Model model) {
        try {
            cardapioService.atualizarCardapio(idCardapio, cardapioDTO);
            return "redirect:/cardapio/cardapio-nutricionista";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            System.out.println(e);
            return "tela-inicial-nutricionista";
        }
    }

}
