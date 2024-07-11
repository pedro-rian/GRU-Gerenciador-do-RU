package br.com.ufrn.imd.gru.repository;

import br.com.ufrn.imd.gru.model.PessoaEvento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioEventoRepository extends JpaRepository<PessoaEvento, Long> {
}
