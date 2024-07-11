package br.com.ufrn.imd.gru.repository;

import br.com.ufrn.imd.gru.model.PessoaBiblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioBibliotecaRepository extends JpaRepository<PessoaBiblioteca, Long> {
}
