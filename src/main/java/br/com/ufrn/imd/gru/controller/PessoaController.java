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
    public ResponseEntity<String>salvarDadosEPeso(Model model, @RequestBody PessoaDTO pessoaDTO) {

        if (pessoaDTO.getNome() == null || pessoaDTO.getEmail() == null || pessoaDTO.getIdade() == 0 ||
                pessoaDTO.getPeso() == 0 || pessoaDTO.getAltura() == 0 || pessoaDTO.getSenha() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Preencha todos os campos");
        }
        if (pessoaService.existeUsuarioComEmail(pessoaDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Já existe um usuário com esse email.");
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
