package br.com.ufrn.imd.gru.repository;

import br.com.ufrn.imd.gru.model.Aviso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AvisoRepository extends JpaRepository<Aviso, Long> {
    Aviso findByData(LocalDate data);
}
