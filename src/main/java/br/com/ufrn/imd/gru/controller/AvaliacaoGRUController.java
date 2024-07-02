package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.AvaliacaoDTO;
import br.com.ufrn.imd.gru.dto.AvaliacaoGRUDTO;
import br.com.ufrn.imd.gru.model.Avaliacao;
import br.com.ufrn.imd.gru.model.AvaliacaoGRU;
import br.com.ufrn.imd.gru.model.Cardapio;
import br.com.ufrn.imd.gru.service.AvaliacaoGRUService;
import br.com.ufrn.imd.gru.service.AvaliacaoService;
import br.com.ufrn.imd.gru.service.CardapioService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public AvaliacaoGRUController(AvaliacaoGRUService avaliacaoService, AvaliacaoGRUService avaliacaoService1, CardapioService cardapioService) {
        super((AvaliacaoService<AvaliacaoGRUDTO>) avaliacaoService);
        this.avaliacaoService = avaliacaoService1;
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
    @Override
    public String cadastrarAvaliacao(AvaliacaoGRUDTO novaAvaliacao, Model model) {
        LocalDate dataAtual = LocalDate.now();

        Cardapio cardapio = cardapioService.findByCardapio(novaAvaliacao.getTipoCardapio(), novaAvaliacao.getTipoRefeicao(), dataAtual);

        if (cardapio != null) {
            novaAvaliacao.setCardapio(cardapio);
            AvaliacaoGRUDTO avaliacao = new AvaliacaoGRUDTO();
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

    @Override
    protected void atualizarAvaliacao(long id, AvaliacaoGRUDTO avaliacaoDto) {
        AvaliacaoGRUDTO avaliacao = avaliacaoService.getById(id);
        if (avaliacao != null) {
            avaliacao.setQuantidadeEstrelas(avaliacaoDto.getQuantidadeEstrelas());
            avaliacao.setDescricao(avaliacaoDto.getDescricao());
            avaliacaoService.atualizar(id, avaliacaoDto);
        } else {
            throw new IllegalArgumentException("Avaliação não encontrada");
        }
    }
}
