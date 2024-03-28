package br.com.ufrn.imd.gru.service;
import br.com.ufrn.imd.gru.model.Usuario;
import br.com.ufrn.imd.gru.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario autenticarUsuario(String email, String senha) {
        return usuarioRepository.autenticar(email, senha);
    }

}
