package br.com.ufrn.imd.gru.repository;

import br.com.ufrn.imd.gru.model.Cardapio;
import br.com.ufrn.imd.gru.model.TipoCardapio;
import br.com.ufrn.imd.gru.model.TipoRefeicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CardapioRepository extends JpaRepository<Cardapio, Long> {
    List<Cardapio> findByData(LocalDate data);

    boolean existsByDataAndTipoCardapioAndTipoRefeicao(LocalDate data, TipoCardapio tipoCardapio, TipoRefeicao tipoRefeicao);

}
