package br.com.ufrn.imd.gru.repository;

import br.com.ufrn.imd.gru.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByUsuarioId(Long idUsuario);

}
