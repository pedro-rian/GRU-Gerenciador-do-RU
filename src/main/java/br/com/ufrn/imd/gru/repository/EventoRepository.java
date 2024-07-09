package br.com.ufrn.imd.gru.repository;

import br.com.ufrn.imd.gru.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
