package br.com.ufrn.imd.gru.controller;

import br.com.ufrn.imd.gru.dto.PessoaDTO;
import br.com.ufrn.imd.gru.model.Pessoa;
import br.com.ufrn.imd.gru.model.Usuario;
import br.com.ufrn.imd.gru.service.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/pessoa")
public class PessoaController {
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/cadastrar-pessoa")
    public String mostrarFormularioCadastro() {
        return "cadastrar-pessoa";
    }

    @PostMapping("/salvar")
    public ResponseEntity<Object> salvarDadosEPeso(Model model, @RequestBody PessoaDTO pessoaDTO) {
        ResponseEntity<List<String>> validationResponse = pessoaService.validarPessoa(pessoaDTO);

        if (validationResponse.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.badRequest().body(validationResponse.getBody());
        }

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setIdade(pessoaDTO.getIdade());
        pessoa.setPeso(pessoaDTO.getPeso());
        pessoa.setAltura(pessoaDTO.getAltura());

        Usuario usuario = new Usuario();
        usuario.setEmail(pessoaDTO.getEmail());
        usuario.setSenha(pessoaDTO.getSenha());

        pessoa.setUsuario(usuario);

        pessoaService.salvarDadosEIMC(pessoa);
        String successMessage = "Usuário e dados salvos com sucesso.";
        return ResponseEntity.ok(successMessage);
    }

}
