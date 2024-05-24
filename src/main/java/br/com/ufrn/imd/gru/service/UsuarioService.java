
package br.com.ufrn.imd.gru.service;
import br.com.ufrn.imd.gru.dto.PessoaDTO;
import br.com.ufrn.imd.gru.model.Pessoa;
import br.com.ufrn.imd.gru.model.TipoUsuario;
import br.com.ufrn.imd.gru.model.Usuario;
import br.com.ufrn.imd.gru.model.UsuarioLogado;
import br.com.ufrn.imd.gru.repository.PessoaRepository;
import br.com.ufrn.imd.gru.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final PessoaRepository pessoaRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PessoaRepository pessoaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public boolean existeUsuarioComEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        return usuario != null;
    }
    public Usuario autenticarUsuario(String email, String senha) {
        return usuarioRepository.autenticar(email, senha);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public void atualizarDadosUsuario(Usuario usuario, PessoaDTO pessoaDTO){
        Optional<Pessoa> pessoa = pessoaRepository.findByUsuarioId(usuario.getId());
        if(pessoa.isPresent()){
            Pessoa p = pessoa.get();
            usuario.setEmail(pessoaDTO.getEmail());
            p.setNome(pessoaDTO.getNome());
            p.setIdade(pessoaDTO.getIdade());
            p.setAltura(pessoaDTO.getAltura());
            p.setPeso(pessoaDTO.getPeso());
            usuarioRepository.save(usuario);
            pessoaRepository.save(p);
        }
    }

    public void salvarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public void desativarUsuario(UsuarioLogado usuarioLogado){
        Usuario usuario = usuarioRepository.findByEmail(usuarioLogado.getEmail());
        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
    }

    public List<Usuario> listarConsumidores() {
        return usuarioRepository.findByTipo(TipoUsuario.CONSUMIDOR);
    }
}
