package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.PessoaDTO;
import br.com.ufrn.imd.gru.dto.PessoaGruDTO;
import br.com.ufrn.imd.gru.model.Pessoa;
import br.com.ufrn.imd.gru.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/pessoa")
public class PessoaController {
    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/cadastrar-pessoa")
    public String mostrarFormularioCadastro() {
        return "cadastrar-pessoa";
    }

    @GetMapping("/cadastrar-pessoa-bib")
    public String mostrarFormularioCadastroBib() {
        return "cadastrar-pessoa-bib";
    }

    @GetMapping("/cadastrar-pessoa-eve")
    public String mostrarFormularioCadastroEve() {
        return "cadastrar-pessoa-eve";
    }

    @PostMapping("/salvar")
    public ResponseEntity<Object> salvarDados(@RequestParam("tipoSistema") String tipoSistema, @RequestBody Pessoa pessoa) {

        pessoaService.setEstrategia(tipoSistema);
        pessoaService.salvarDados(pessoa);
        return ResponseEntity.ok().build();

//
//        if (validationResponse.getStatusCode() != HttpStatus.OK) {
//            return ResponseEntity.badRequest().body(validationResponse.getBody());
//        }
//
//        Pessoa pessoa = new Pessoa();
//        pessoa.setNome(pessoaDTO.getNome());
//        pessoa.setIdade(pessoaDTO.getIdade());
//        pessoa.setPeso(pessoaDTO.getPeso());
//        pessoa.setAltura(pessoaDTO.getAltura());
//
//        Usuario usuario = new Usuario();
//        usuario.setEmail(pessoaDTO.getEmail());
//        usuario.setSenha(pessoaDTO.getSenha());
//        usuario.setTipo(pessoaDTO.getTipoUsuario());
//
//        pessoa.setUsuario(usuario);
//
//        pessoaGRUService.salvarDados(pessoa);
//        String successMessage = "Usuário e dados salvos com sucesso.";
//        return ResponseEntity.ok(successMessage);
    }

}
