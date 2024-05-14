
package br.com.ufrn.imd.gru.service;
import br.com.ufrn.imd.gru.model.TipoUsuario;
import br.com.ufrn.imd.gru.model.Usuario;
import br.com.ufrn.imd.gru.model.UsuarioLogado;
import br.com.ufrn.imd.gru.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean existeUsuarioComEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        return usuario != null;
    }
    public Usuario autenticarUsuario(String email, String senha) {
        return usuarioRepository.autenticar(email, senha);
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
