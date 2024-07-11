package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.service.EstatisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class EstatisticaController {

    private final EstatisticaService estatisticaService;

    @Autowired
    public EstatisticaController(EstatisticaService estatisticaService) {
        this.estatisticaService = estatisticaService;
    }

    @PostMapping("/estatisticas")
    public String mostrarEstatisticas(@RequestParam("tipoSistema") String tipoSistema, Model model) {
        estatisticaService.setEstrategia(tipoSistema);

        long totalUsuarios = estatisticaService.calcularTotalUsuarios();
        Map<String, Double> totalArtefatos = estatisticaService.calcularTotalArtefatos();
        Map<String, Double> medias = estatisticaService.calcularMedias();

        model.addAttribute("totalUsuarios", totalUsuarios);
        model.addAttribute("totalArtefatos", totalArtefatos);
        model.addAttribute("medias", medias);

        return "estatisticas";
    }
}
