package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.PessoaGruDTO;
import br.com.ufrn.imd.gru.model.*;

import br.com.ufrn.imd.gru.repository.*;
import br.com.ufrn.imd.gru.service.PessoaGRUService;
import br.com.ufrn.imd.gru.service.UsuarioService;
import br.com.ufrn.imd.gru.service.AssistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private UsuarioService usuarioService;
    private PessoaGRUService pessoaGRUService;
    private UsuarioLogado usuarioLogado;

    private AssistenciaRepository assistenciaRepository;
    private PessoaRepository pessoaRepository;
    private AvisoRepository avisoRepository;
    private CardapioRepository cardapioRepository;
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, AssistenciaService assistenciaService,
                             PessoaGRUService pessoaGRUService, PessoaRepository pessoaRepository,
                             AssistenciaRepository assistenciaRepository, AvisoRepository avisoRepository,
                             CardapioRepository cardapioRepository, AvaliacaoRepository avaliacaoRepository,
                             UsuarioLogado usuarioLogado, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.assistenciaRepository = assistenciaRepository;
        this.pessoaGRUService = pessoaGRUService;
        this.pessoaRepository = pessoaRepository;
        this.avisoRepository = avisoRepository;
        this.cardapioRepository = cardapioRepository;
        this.avaliacaoRepository = avaliacaoRepository;
        this.usuarioLogado = usuarioLogado;
        this.usuarioRepository = usuarioRepository;
    }

    public PessoaGRU getPessoaByUsuarioId(Long usuarioId) {
        Optional<PessoaGRU> pessoa = this.pessoaRepository.findByUsuarioId(usuarioId);
        return pessoa.orElse(null);
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/login-bib")
    public String loginBib(){
        return "login-bib";
    }

    @GetMapping("/login-eve")
    public String loginEve(){
        return "login-eve";
    }

    @PostMapping("/logar")
    public String logar(Model model, String email, String senha){
        Usuario usuario = usuarioService.autenticarUsuario(email, senha);
        Usuario u = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            if (u.getTipo() == TipoUsuario.CONSUMIDOR){
                usuarioLogado.setEmail(email);
                usuarioLogado.setSenha(senha);
                PessoaGRU p = getPessoaByUsuarioId(u.getId());
                usuarioLogado.setNome(p.getNome());
                usuarioLogado.setIdade(p.getIdade());
                usuarioLogado.setPeso(p.getPeso());
                usuarioLogado.setAltura(p.getAltura());
            }
            if (usuario.getTipo().equals(TipoUsuario.CONSUMIDOR)) {
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
        List<PessoaGRU> pessoaGRUS = new ArrayList<>();

        for (Usuario consumidor : consumidores) {
            Long usuarioId = consumidor.getId();
            PessoaGRU pessoaGRU = pessoaGRUService.buscarPorIdDoUsuario(usuarioId);
            if (pessoaGRU != null) {
                pessoaGRUS.add(pessoaGRU);
            }
        }

        model.addAttribute("consumidores", consumidores);
        model.addAttribute("pessoas", pessoaGRUS);
        return "tela-inicial-nutricionista";
    }

    @GetMapping("/tela-inicial-bib")
    public String telaInicialBib(Model model) {
        return "tela-inicial-bib";
    }

    @GetMapping("/tela-inicial-eve")
    public String telaInicialEve(Model model) {
        return "tela-inicial-eve";
    }


    @GetMapping("/sobre-o-ru")
    public String sobreORu() {
        return "sobre-o-ru";
    }
    @GetMapping("/meu-perfil")
    public String meuPerfil(Model model) {
        model.addAttribute("email", usuarioLogado.getEmail());
        model.addAttribute("senha", usuarioLogado.getSenha());
        model.addAttribute("nome", usuarioLogado.getNome());
        model.addAttribute("idade", usuarioLogado.getIdade());
        model.addAttribute("peso", usuarioLogado.getPeso());
        model.addAttribute("altura", usuarioLogado.getAltura());
        return "meu-perfil";
    }

    @PostMapping("meu-perfil/apagar-conta")
    public String apagarConta(){
        usuarioService.desativarUsuario(usuarioLogado);
        return "redirect:/usuario/login";
    }

    @GetMapping("meu-perfil/editar-dados")
    public String editarDados(Model model) {
        model.addAttribute("email", usuarioLogado.getEmail());
        model.addAttribute("nome", usuarioLogado.getNome());
        model.addAttribute("idade", usuarioLogado.getIdade());
        model.addAttribute("peso", usuarioLogado.getPeso());
        model.addAttribute("altura", usuarioLogado.getAltura());
        return "editar-dados";
    }

    @PostMapping("/salvar-dados")
    public String salvarDados(@ModelAttribute PessoaGruDTO pessoaGRUDTO){
        Usuario usuario = usuarioService.findByEmail(usuarioLogado.getEmail());
        usuarioService.atualizarDadosUsuario(usuario, pessoaGRUDTO);
        return "redirect:/usuario/login";
    }

    @GetMapping("/avaliacoes")
    public String avaliacoes() {
        return "avaliacoes";
    }
    @GetMapping("/solicitar-assistencia")
    public String solicitarAssistencia() {
        return "solicitar-assistencia";
    }

    @GetMapping("/cadastrar-nutricionista")
    public String cadastrarNutricionista(){return "cadastrar-nutricionista";}


}
