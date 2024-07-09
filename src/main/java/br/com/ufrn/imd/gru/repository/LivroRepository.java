package br.com.ufrn.imd.gru.repository;

import br.com.ufrn.imd.gru.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
