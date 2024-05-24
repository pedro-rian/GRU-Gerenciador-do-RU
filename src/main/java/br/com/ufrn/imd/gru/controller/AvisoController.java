package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.AvisoDTO;
import br.com.ufrn.imd.gru.model.Aviso;
import br.com.ufrn.imd.gru.service.AvisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/aviso")
public class AvisoController {

    private AvisoService avisoService;

    @Autowired
    public AvisoController(AvisoService avisoService) {
        this.avisoService = avisoService;
    }

    @GetMapping("/cadastrar-aviso-nutricionista")
    public String cadastrarAvisoNutricionista(Model model){
        List<Aviso> avisos = avisoService.getAvisos();
        model.addAttribute("avisos", avisos);
        return "cadastrar-aviso-nutricionista";
    }

    @PostMapping("/cadastrar-aviso-nutricionista/cadastrar-aviso")
    public String cadastrarAviso(@ModelAttribute AvisoDTO avisoDTO, Model model) {
        try {
            avisoService.cadastrarAviso(avisoDTO);
            return "redirect:/usuario/tela-inicial-nutricionista";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("avisoDTO", avisoDTO);

            return "cadastrar-aviso-nutricionista";
        }
    }

    @PostMapping("/excluir-aviso/{id}")
    public String excluirAviso(@PathVariable long id) {
        avisoService.deleteById(id);
        return "redirect:/aviso/cadastrar-aviso-nutricionista";
    }

    @GetMapping("/editar-aviso/{id}")
    public String editarAviso(@PathVariable long id, Model model) {
        Aviso aviso = avisoService.findById(id);
        model.addAttribute("aviso", aviso);
        return "editar-aviso";
    }

    @PostMapping("/atualizar-aviso")
    public String atualizarAviso(@RequestParam("id_aviso") long id, @ModelAttribute AvisoDTO avisoDTO, Model model) {
        avisoService.atualizarAviso(id, avisoDTO);
        return "redirect:/aviso/cadastrar-aviso-nutricionista";
    }
}
