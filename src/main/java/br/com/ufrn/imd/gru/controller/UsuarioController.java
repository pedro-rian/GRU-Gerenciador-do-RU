package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.PessoaDTO;
import br.com.ufrn.imd.gru.model.*;

import br.com.ufrn.imd.gru.repository.*;
import br.com.ufrn.imd.gru.service.PessoaService;
import br.com.ufrn.imd.gru.service.UsuarioService;
import br.com.ufrn.imd.gru.service.AssistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private UsuarioService usuarioService;
    private PessoaService pessoaService;
    private UsuarioLogado usuarioLogado;

    private AssistenciaRepository assistenciaRepository;
    private PessoaRepository pessoaRepository;
    private AvisoRepository avisoRepository;
    private CardapioRepository cardapioRepository;
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, AssistenciaService assistenciaService,
                             PessoaService pessoaService, PessoaRepository pessoaRepository,
                             AssistenciaRepository assistenciaRepository, AvisoRepository avisoRepository,
                             CardapioRepository cardapioRepository, AvaliacaoRepository avaliacaoRepository,
                             UsuarioLogado usuarioLogado, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.assistenciaRepository = assistenciaRepository;
        this.pessoaService = pessoaService;
        this.pessoaRepository = pessoaRepository;
        this.avisoRepository = avisoRepository;
        this.cardapioRepository = cardapioRepository;
        this.avaliacaoRepository = avaliacaoRepository;
        this.usuarioLogado = usuarioLogado;
        this.usuarioRepository = usuarioRepository;
    }

    public Pessoa getPessoaByUsuarioId(Long usuarioId) {
        Optional<Pessoa> pessoa = this.pessoaRepository.findByUsuarioId(usuarioId);
        return pessoa.orElse(null);
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/logar")
    public String logar(Model model, String email, String senha){
        Usuario usuario = usuarioService.autenticarUsuario(email, senha);
        Usuario u = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            if (u.getTipo() == TipoUsuario.CONSUMIDOR){
                usuarioLogado.setEmail(email);
                usuarioLogado.setSenha(senha);
                Pessoa p = getPessoaByUsuarioId(u.getId());
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
    public String salvarDados(@ModelAttribute PessoaDTO pessoaDTO){
        Usuario usuario = usuarioService.findByEmail(usuarioLogado.getEmail());
        usuarioService.atualizarDadosUsuario(usuario, pessoaDTO);
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

    @GetMapping("/estatisticas-nutricionista")
    public String estatisticasNutricionista(Model model){
        //Total de usuarios:
        long total_usuarios = pessoaRepository.count();
        model.addAttribute("total_usuarios", total_usuarios);
        //Total de assistencias:
        long total_assistencias = assistenciaRepository.count();
        model.addAttribute("total_assistencias", total_assistencias);
        //Total de avisos:
        long total_avisos = avisoRepository.count();
        model.addAttribute("total_avisos", total_avisos);
        //Total de cardapios:
        long total_cardapios = cardapioRepository.count();
        model.addAttribute("total_cardapios", total_cardapios);
        //Total de avaliacoes:
        long total_avaliacoes = avaliacaoRepository.count();
        model.addAttribute("total_avaliacoes", total_avaliacoes);
        //Media dos IMCs:
        double media_imc = mediaIMC();
        model.addAttribute("media_imc", media_imc);
        //Media dos pesos:
        double media_peso = mediaPeso();
        model.addAttribute("media_peso", media_peso);
        //Media dos pesos:
        double media_altura = mediaAltura();
        model.addAttribute("media_altura", media_altura);
        //Percentuais dos IMCs:
        double[] percentuais_imc = percentualIMC();
        model.addAttribute("baixo", percentuais_imc[0]);
        model.addAttribute("normal", percentuais_imc[1]);
        model.addAttribute("sobrepeso", percentuais_imc[2]);
        model.addAttribute("obesidade", percentuais_imc[3]);

        return "estatisticas-nutricionista";
    }

    @GetMapping("/cadastrar-nutricionista")
    public String cadastrarNutricionista(){return "cadastrar-nutricionista";}

    //Funcoes para estatisticas:
    private double mediaIMC(){
        List<Pessoa> pessoas = pessoaRepository.findAll();
        if(pessoas.isEmpty())
            return 0.0;

        double somaIMC = 0.0;
        for(Pessoa p : pessoas)
            somaIMC += p.getImc();

        double mediaIMC = somaIMC/pessoaRepository.count();
        return mediaIMC;
    }

    private double mediaPeso(){
        List<Pessoa> pessoas = pessoaRepository.findAll();
        if(pessoas.isEmpty())
            return 0.0;

        double somaPeso = 0.0;
        for(Pessoa p : pessoas)
            somaPeso += p.getPeso();

        double mediaPeso = somaPeso/pessoaRepository.count();
        return mediaPeso;
    }

    private double mediaAltura(){
        List<Pessoa> pessoas = pessoaRepository.findAll();
        if(pessoas.isEmpty())
            return 0.0;

        double somaAltura = 0.0;
        for(Pessoa p : pessoas)
            somaAltura += p.getAltura();

        double mediaAltura = somaAltura/pessoaRepository.count();
        return mediaAltura;
    }

    private double[] percentualIMC(){
        List<Pessoa> pessoas = pessoaRepository.findAll();
        if(pessoas.isEmpty()){
            double[] zeros = {0, 0, 0, 0};
            return zeros;
        }
        double[] listaPercentualIMC = {0, 0, 0, 0};
        for (Pessoa p : pessoas){
            if(p.getImc() < 20)
                listaPercentualIMC[0]+= 1;
            if(p.getImc() >= 20 && p.getImc() < 25)
                listaPercentualIMC[1]+= 1;
            if(p.getImc() >= 25 && p.getImc() < 30)
                listaPercentualIMC[2]+= 1;
            if (p.getImc() >= 30)
                listaPercentualIMC[3]+= 1;
        }

        double[] percentuais = {0, 0, 0, 0};
        double total = Arrays.stream(listaPercentualIMC).sum();
        for(int i = 0; i < listaPercentualIMC.length; i++){
            percentuais[i] = (listaPercentualIMC[i]/total)*100;
        }

        return percentuais;
    }


}
