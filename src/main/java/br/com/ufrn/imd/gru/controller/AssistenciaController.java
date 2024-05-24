package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.AssistenciaDTO;
import br.com.ufrn.imd.gru.model.Assistencia;
import br.com.ufrn.imd.gru.service.AssistenciaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Assistencia> assistenciasHoje = assistenciaService.listarAssistenciasDaDataAtual();
        List<Assistencia> assistencias = assistenciaService.listarTodasAssistencias();
        model.addAttribute("assistenciasHoje", assistenciasHoje);
        model.addAttribute("assistencias", assistencias);
        return "solicitacoes-assistencia-nutricionista";
    }

    @GetMapping("/assistencias")
    public String listarAssistencias(Model model) {
        List<Assistencia> assistencias = assistenciaService.listarTodasAssistencias();
        model.addAttribute("assistencias", assistencias);
        return "assistencias";
    }

    @PostMapping("/excluir/{id}")
    public String excluirAssistencia(@PathVariable long id) {
        assistenciaService.deleteById(id);
        return "redirect:/assistencia/assistencias";
    }

    @GetMapping("/editar/{id}")
    public String editarAssistencia(@PathVariable long id, Model model) {
        Assistencia assistencia = assistenciaService.findById(id);
        model.addAttribute("assistencia", assistencia);
        return "editar-assistencia";
    }

    @PostMapping("/atualizar")
    public String atualizarAssistencia(@RequestParam("id_assistencia") long id, @ModelAttribute AssistenciaDTO assistenciaDTO) {
        assistenciaService.atualizarAssistencia(id, assistenciaDTO);
        return "redirect:/assistencia/assistencias";
    }


}
