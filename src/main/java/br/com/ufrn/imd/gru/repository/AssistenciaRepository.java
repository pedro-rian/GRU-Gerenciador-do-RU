package br.com.ufrn.imd.gru.repository;

import br.com.ufrn.imd.gru.model.Assistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AssistenciaRepository extends JpaRepository<Assistencia, Long> {
    List<Assistencia> findByData(LocalDate data);
}
