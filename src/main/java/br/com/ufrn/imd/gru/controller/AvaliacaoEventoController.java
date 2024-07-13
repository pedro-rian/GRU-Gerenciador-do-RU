package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.AvaliacaoEventoDTO;
import br.com.ufrn.imd.gru.dto.EventoDto;
import br.com.ufrn.imd.gru.service.AvaliacaoEventoService;
import br.com.ufrn.imd.gru.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/avaliacoes/evento")
public class AvaliacaoEventoController extends AvaliacaoController<AvaliacaoEventoDTO> {

    private final AvaliacaoEventoService avaliacaoService;
    private final EventoService eventoService;

    @Autowired
    public AvaliacaoEventoController(AvaliacaoEventoService avaliacaoService, EventoService eventoService) {
        super(avaliacaoService);
        this.avaliacaoService = avaliacaoService;
        this.eventoService = eventoService;
    }

    @GetMapping
    public String listarAvaliacoes(Model model) {
        List<EventoDto> eventos = eventoService.getEventosAtuais();
        List<AvaliacaoEventoDTO> avaliacoes = avaliacaoService.buscarAvaliacoesAtuais();
        model.addAttribute("avaliacoes", avaliacoes);
        model.addAttribute("eventos", eventos);
        return "avaliacoes-evento";
    }

    @PostMapping
    protected String cadastrarAvaliacao(AvaliacaoEventoDTO avaliacaoDto, Model model) {
        AvaliacaoEventoDTO avaliacao = new AvaliacaoEventoDTO();
        avaliacao.setDescricao(avaliacaoDto.getDescricao());
        avaliacao.setEstrelasAcessibilidade(avaliacaoDto.getEstrelasAcessibilidade());
        avaliacao.setEstrelasPontualidade(avaliacaoDto.getEstrelasPontualidade());
        avaliacao.setEstrelasPalestrante(avaliacaoDto.getEstrelasPalestrante());
        avaliacao.setEvento(avaliacaoDto.getEvento());
        avaliacaoService.cadastrar(avaliacao);
        return "avaliacoes-evento";
    }

    @GetMapping("/editar/{id}")
    public String editarAvaliacaoForm(@PathVariable Long id, Model model) {
        AvaliacaoEventoDTO avaliacao = avaliacaoService.getById(id);
        if (avaliacao != null) {
            model.addAttribute("avaliacao", avaliacao);
            model.addAttribute("evento", avaliacao.getEvento());
            return "editar-avaliacao-evento";
        } else {
            throw new IllegalArgumentException("Avaliação não encontrada");
        }
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarAvaliacao(@PathVariable("id") Long id, @ModelAttribute AvaliacaoEventoDTO avaliacaoDto, Model model) {
        AvaliacaoEventoDTO avaliacao = avaliacaoService.getById(id);

        if (avaliacao != null) {
            avaliacao.setDescricao(avaliacaoDto.getDescricao());
            avaliacao.setEstrelasPontualidade(avaliacaoDto.getEstrelasPontualidade());
            avaliacao.setEstrelasAcessibilidade(avaliacaoDto.getEstrelasAcessibilidade());
            avaliacao.setEstrelasPalestrante(avaliacaoDto.getEstrelasPalestrante());
            avaliacaoService.atualizar(id, avaliacao);
            model.addAttribute("successMessage", "Avaliação atualizada com sucesso!");
            return "redirect:/avaliacoes/evento";
        } else {
            model.addAttribute("errorMessage", "Avaliação não encontrada");
            return "editar-avaliacao";
        }
    }





}
