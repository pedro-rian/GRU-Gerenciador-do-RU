package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.entity.Usuario;
import br.com.ufrn.imd.gru.repository.UsuarioRepository;
import br.com.ufrn.imd.gru.service.UsuarioService;

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
public class UsuarioController {

    private UsuarioService usuarioService;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/logar")
    public String logar(Model model, String email, String senha){
        Usuario usuario = usuarioService.autenticarUsuario(email, senha);
        if (usuario != null) {
            if (usuario.getTipo().equals("administrador")) {
                return "tela-inicial-administrador";
            } else if (usuario.getTipo().equals("comum")) {
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