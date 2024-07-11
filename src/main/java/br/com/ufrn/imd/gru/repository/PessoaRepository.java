package br.com.ufrn.imd.gru.repository;

import br.com.ufrn.imd.gru.model.PessoaGRU;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<PessoaGRU, Long> {

    Optional<PessoaGRU> findByUsuarioId(Long idUsuario);

}
