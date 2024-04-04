package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.service.AvisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/aviso")
public class AvisoController {

    private AvisoService avisoService;

    @Autowired
    public AvisoController(AvisoService avisoService) {
        this.avisoService = avisoService;
    }

    @GetMapping("/cadastrar-aviso-nutricionista")
    public String cadastrarAvisoNutricionista(){return "cadastrar-aviso-nutricionista";}
    @PostMapping("/cadastrar-aviso-nutricionista/cadastrar-aviso")
    public String cadastrarAviso(@RequestParam("titulo") String titulo,
                                 @RequestParam("descricao") String descricao,
                                 @RequestParam(value = "data", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String dataString,
                                 Model model) {
        try {
            avisoService.cadastrarAviso(titulo, descricao, dataString);
            return "redirect:/usuario/tela-inicial-nutricionista";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "cadastrar-aviso-nutricionista";
        }
    }
}
