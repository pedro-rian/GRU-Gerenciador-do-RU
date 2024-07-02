
package br.com.ufrn.imd.gru.repository;

import br.com.ufrn.imd.gru.model.TipoUsuario;
import br.com.ufrn.imd.gru.model.UsuarioGRU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioGRURepository extends JpaRepository<UsuarioGRU, Long> {
    @Query(value ="select *from  usuario where email =:email and senha =:senha and ativo=true", nativeQuery = true)
    public UsuarioGRU autenticar(String email, String senha);

    public UsuarioGRU findByEmail(String email);

    List<UsuarioGRU> findByTipo(TipoUsuario tipo);
}
