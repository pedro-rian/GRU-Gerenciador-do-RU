package br.com.ufrn.imd.gru.repository;

import br.com.ufrn.imd.gru.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
