package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.AssistenciaDTO;
import br.com.ufrn.imd.gru.service.AssistenciaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/assistencia")
public class AssistenciaController {
    AssistenciaService assistenciaService;

    public AssistenciaController(AssistenciaService assistenciaService) {
        this.assistenciaService = assistenciaService;
    }

    @PostMapping("/solicitar-assistencia")
    public String cadastrarSolicitacao(@ModelAttribute AssistenciaDTO assistenciaDTO, Model model) {
        try {
            assistenciaService.cadastrarAssistencia(assistenciaDTO);
            return "redirect:/cardapio/tela-inicial-comum";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "solicitar-assistencia";
        }
    }

    @GetMapping("/solicitacoes-assistencia-nutricionista")
    public String solicitacoesAssistenciaNutricionista(Model model) {
        List<AssistenciaDTO> assistenciasHoje = assistenciaService.listarAssistenciasDaDataAtual();
        List<AssistenciaDTO> assistencias = assistenciaService.listarTodasAssistencias();
        model.addAttribute("assistenciasHoje", assistenciasHoje);
        model.addAttribute("assistencias", assistencias);
        return "solicitacoes-assistencia-nutricionista";
    }
}
