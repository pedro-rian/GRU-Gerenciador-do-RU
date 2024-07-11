
package br.com.ufrn.imd.gru.service;
import br.com.ufrn.imd.gru.dto.PessoaGruDTO;
import br.com.ufrn.imd.gru.model.PessoaGRU;
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

    public void atualizarDadosUsuario(Usuario usuario, PessoaGruDTO pessoaGRUDTO){
        Optional<PessoaGRU> pessoa = pessoaRepository.findByUsuarioId(usuario.getId());
        if(pessoa.isPresent()){
            PessoaGRU p = pessoa.get();
            usuario.setEmail(pessoaGRUDTO.getEmail());
            p.setNome(pessoaGRUDTO.getNome());
            p.setIdade(pessoaGRUDTO.getIdade());
            p.setAltura(pessoaGRUDTO.getAltura());
            p.setPeso(pessoaGRUDTO.getPeso());
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
