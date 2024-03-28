package br.com.ufrn.imd.gru.repository;

import br.com.ufrn.imd.gru.entity.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardapioRepository extends JpaRepository<Cardapio, Long> {
}
