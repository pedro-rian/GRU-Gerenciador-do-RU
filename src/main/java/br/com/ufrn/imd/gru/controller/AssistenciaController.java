package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.AssistenciaDTO;
import br.com.ufrn.imd.gru.service.AssistenciaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/assistencia")
public class AssistenciaController {
    AssistenciaService assistenciaService;

    public AssistenciaController(AssistenciaService assistenciaService) {
        this.assistenciaService = assistenciaService;
    }

    @PostMapping("/solicitar-assistencia")
    public String cadastrarSolicitacao(@ModelAttribute AssistenciaDTO assistenciaDTO, Model model) {
        System.out.println("Motivo: " + assistenciaDTO.getMotivo());
        System.out.println("Descricao: " + assistenciaDTO.getDescricao());
        System.out.println("Data: " + assistenciaDTO.getData());
        System.out.println("Horario: " + assistenciaDTO.getHorario());
        try {
            assistenciaService.cadastrarAssistencia(assistenciaDTO);
            return "redirect:/cardapio/tela-inicial-comum";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "solicitar-assistencia";
        }
    }
}
