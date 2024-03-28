package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.model.Pessoa;
import br.com.ufrn.imd.gru.service.PessoaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin("*")
@RequestMapping("/usuario")
public class PessoaController {

    private PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/logar")
    public String logar(Model model, String email, String senha){
        Pessoa pessoa = pessoaService.autenticarUsuario(email, senha);
        if (pessoa != null) {
            if (pessoa.getTipo().equals("administrador")) {
                return "tela-inicial-administrador";
            } else if (pessoa.getTipo().equals("comum")) {
                return "tela-inicial-comum";
            } else {
                return "login";
            }
        }else{
            model.addAttribute("erro", "Usuário ou senha inválidos");
            return "login";
        }
    }

}
