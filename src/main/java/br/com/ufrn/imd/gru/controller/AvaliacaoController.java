package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping("/cadastrar")
    public String cadastrar(@RequestParam("nome") String nome,
                            @RequestParam("email") String email,
                            @RequestParam("descricao") String descricao, Model model) {
        try {
            avaliacaoService.cadastrarAvaliacao(nome, email, descricao);
            model.addAttribute("success", "Avaliação cadastrada com sucesso!");
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao tentar cadastrar avaliação");
        }

        return "avaliacoes";
    }
}
