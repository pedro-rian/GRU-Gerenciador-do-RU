package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.AvaliacaoEventoDTO;
import br.com.ufrn.imd.gru.model.Evento;
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
        List<Evento> eventos = eventoService.getEventosAtuais();
        List<AvaliacaoEventoDTO> avaliacoes = avaliacaoService.buscarTodasAvaliacoes();
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

    protected void atualizarAvaliacao(long id, AvaliacaoEventoDTO avaliacaoDto) {
        avaliacaoService.atualizar(id, avaliacaoDto);
    }
}
