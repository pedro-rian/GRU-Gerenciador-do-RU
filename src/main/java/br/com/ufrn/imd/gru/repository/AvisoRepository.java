package br.com.ufrn.imd.gru.repository;

import br.com.ufrn.imd.gru.model.Aviso;
import br.com.ufrn.imd.gru.model.TipoCardapio;
import br.com.ufrn.imd.gru.model.TipoRefeicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AvisoRepository extends JpaRepository<Aviso, Long> {
    Aviso findByData(LocalDate data);
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Aviso a WHERE a.data = :data")
    boolean existsAviso(LocalDate data);

}
