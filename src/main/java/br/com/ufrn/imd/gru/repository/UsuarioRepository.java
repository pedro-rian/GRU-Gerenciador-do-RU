
package br.com.ufrn.imd.gru.repository;

import br.com.ufrn.imd.gru.model.TipoUsuario;
import br.com.ufrn.imd.gru.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query(value ="select *from  usuario where email =:email and senha =:senha and ativo=true", nativeQuery = true)
    public Usuario autenticar(String email, String senha);

    public Usuario findByEmail(String email);

    List<Usuario> findByTipo(TipoUsuario tipo);
}
