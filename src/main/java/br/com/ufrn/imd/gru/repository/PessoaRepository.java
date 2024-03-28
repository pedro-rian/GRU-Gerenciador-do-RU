package br.com.ufrn.imd.gru.repository;

import br.com.ufrn.imd.gru.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query(value ="select *from pessoa where email =:email and senha =:senha", nativeQuery = true)
    public Pessoa autenticar(String email, String senha);
 }
