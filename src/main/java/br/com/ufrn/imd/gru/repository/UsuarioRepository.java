package br.com.ufrn.imd.gru.repository;

import br.com.ufrn.imd.gru.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query(value ="select *from  usuario where email =:email and senha =:senha", nativeQuery = true)
    public Usuario autenticar(String email, String senha);
 }
