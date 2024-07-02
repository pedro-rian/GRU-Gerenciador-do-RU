package br.com.ufrn.imd.gru.repository;

import br.com.ufrn.imd.gru.model.AvaliacaoGRU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoGRU, Long> {
}
