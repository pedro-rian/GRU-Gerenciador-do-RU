package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.AvaliacaoEventoDTO;
import br.com.ufrn.imd.gru.model.AvaliacaoEvento;
import br.com.ufrn.imd.gru.service.AvaliacaoEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacoes/evento")
public class AvaliacaoEventoController extends AvaliacaoController<AvaliacaoEventoDTO> {

    private final AvaliacaoEventoService avaliacaoService;

    @Autowired
    public AvaliacaoEventoController(AvaliacaoEventoService avaliacaoService) {
        super(avaliacaoService);
        this.avaliacaoService = avaliacaoService;
    }

    @Override
    protected String cadastrarAvaliacao(AvaliacaoEventoDTO avaliacaoDto, Model model) {
        AvaliacaoEventoDTO avaliacao = new AvaliacaoEventoDTO();
        avaliacao.setDescricao(avaliacaoDto.getDescricao());
        avaliacao.setEstrelasAcessibilidade(avaliacaoDto.getEstrelasAcessibilidade());
        avaliacao.setEstrelasPontualidade(avaliacaoDto.getEstrelasPontualidade());
        avaliacao.setEstrelasPalestrante(avaliacaoDto.getEstrelasPalestrante());
        avaliacao.setEvento(avaliacaoDto.getEvento());
        avaliacaoService.cadastrar(avaliacao);
        return "redirect:/avaliacoes/evento/cadastrar";
    }

    @Override
    protected void atualizarAvaliacao(long id, AvaliacaoEventoDTO avaliacaoDto) {
        avaliacaoService.atualizar(id, avaliacaoDto);
    }
}
