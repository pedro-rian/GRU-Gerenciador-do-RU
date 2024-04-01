package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.model.TipoUsuario;
import br.com.ufrn.imd.gru.model.Usuario;

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

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/logar")
    public String logar(Model model, String email, String senha){
        Usuario usuario = usuarioService.autenticarUsuario(email, senha);
        if (usuario != null) {
            if (usuario.getTipo().equals(TipoUsuario.ADMINISTRADOR)) {
                return "redirect:/usuario/tela-inicial-administrador";
            } else if (usuario.getTipo().equals(TipoUsuario.CONSUMIDOR)) {
                return "redirect:/cardapio/tela-inicial-comum";
            } else if (usuario.getTipo().equals(TipoUsuario.NUTRICIONISTA)) {
                return "redirect:/usuario/tela-inicial-nutricionista";
            }
            else {
                return "login";
            }
        }else{
            model.addAttribute("erro", "Usuário ou senha inválidos");
            return "login";
        }
    }


    //Mapeamento das páginas de CONSUMIDOR:
    @GetMapping("/sobre-o-ru")
    public String sobreORu() {
        return "sobre-o-ru";
    }

    @GetMapping("/meu-perfil")
    public String meuPerfil() {
        return "meu-perfil";
    }

    @GetMapping("/avaliacoes")
    public String avaliacoes() {
        return "avaliacoes";
    }
    @GetMapping("/solicitar-assistencia")
    public String solicitarAssistencia() {
        return "solicitar-assistencia";
    }

    //Mapeamento das páginas de NUTRICIONISTA:
    @GetMapping("/tela-inicial-nutricionista")
    public String telaInicialNutricionista(){return "tela-inicial-nutricionista";}
    @GetMapping("/meu-perfil-nutricionista")
    public String meuPerfilNutricionista(){return "meu-perfil-nutricionista";}
    @GetMapping("/estatisticas-nutricionista")
    public String estatisticasNutricionista(){return "estatisticas-nutricionista";}
    @GetMapping("/avaliacoes-nutricionista")
    public String avaliacoesNutricionista(){return "avaliacoes-nutricionista";}

    @GetMapping("/cadastrar-aviso-nutricionista")
    public String cadastrarAvisoNutricionista(){return "cadastrar-aviso-nutricionista";}
    @GetMapping("/solicitacoes-assistencia-nutricionista")
    public String solicitacoesAssistenciaNutricionista(){return "solicitacoes-assistencia-nutricionista";}


}
