package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.model.Pessoa;
import br.com.ufrn.imd.gru.model.TipoUsuario;
import br.com.ufrn.imd.gru.model.Usuario;

import br.com.ufrn.imd.gru.repository.AssistenciaRepository;
import br.com.ufrn.imd.gru.service.PessoaService;
import br.com.ufrn.imd.gru.service.UsuarioService;
import br.com.ufrn.imd.gru.service.AssistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;
    private AssistenciaService assistenciaService;

    private PessoaService pessoaService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, AssistenciaService assistenciaService, PessoaService pessoaService) {
        this.usuarioService = usuarioService;
        this.assistenciaService = assistenciaService;
        this.pessoaService = pessoaService;
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
    @GetMapping("/tela-inicial-nutricionista")
    public String listarConsumidores(Model model) {
        List<Usuario> consumidores = usuarioService.listarConsumidores();
        List<Pessoa> pessoas = new ArrayList<>();

        for (Usuario consumidor : consumidores) {
            Long usuarioId = consumidor.getId();
            Pessoa pessoa = pessoaService.buscarPorIdDoUsuario(usuarioId);
            if (pessoa != null) {
                pessoas.add(pessoa);
            }
        }

        model.addAttribute("consumidores", consumidores);
        model.addAttribute("pessoas", pessoas);
        return "tela-inicial-nutricionista";
    }


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

    @GetMapping("/meu-perfil-nutricionista")
    public String meuPerfilNutricionista(){return "meu-perfil-nutricionista";}
    @GetMapping("/estatisticas-nutricionista")
    public String estatisticasNutricionista(){return "estatisticas-nutricionista";}
    @GetMapping("/avaliacoes-nutricionista")
    public String avaliacoesNutricionista(){return "avaliacoes-nutricionista";}


    @GetMapping("/solicitacoes-assistencia-nutricionista")
    public String solicitacoesAssistenciaNutricionista(){return "solicitacoes-assistencia-nutricionista";}

    @PostMapping("/solicitar-assistencia/solicitar-assistencia-form")
    public String cadastrarCardapio(@RequestParam("motivo") String motivo,
                                    @RequestParam("descricao") String descricao,
                                    @RequestParam(value = "data", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String dataString,
                                    @RequestParam("horario") String horarioString,
                                    Model model) {

        try {
            assistenciaService.cadastrarAssistencia(motivo, descricao, dataString, horarioString);
            return "redirect:/cardapio/tela-inicial-comum";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "solicitar-assistencia";
        }
    }


}
