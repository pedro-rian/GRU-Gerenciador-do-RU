package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.AvaliacaoEventoDTO;
import br.com.ufrn.imd.gru.model.AvaliacaoEvento;
import br.com.ufrn.imd.gru.repository.AvaliacaoEventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoEventoService extends AvaliacaoService<AvaliacaoEventoDTO> {
        private final AvaliacaoEventoRepository avaliacaoEventoRepository;

        @Autowired
        public AvaliacaoEventoService(AvaliacaoEventoRepository avaliacaoEventoRepository) {
            this.avaliacaoEventoRepository = avaliacaoEventoRepository;
        }

        public void cadastrar(AvaliacaoEvento avaliacaoEvento) {
            avaliacaoEventoRepository.save(avaliacaoEvento);
        }

        public void atualizar(AvaliacaoEvento avaliacaoEvento) {
            avaliacaoEventoRepository.save(avaliacaoEvento);
        }

        public void deleteById(long id) {
            avaliacaoEventoRepository.deleteById(id);
        }

        public AvaliacaoEvento getById(long id) {
            Optional<AvaliacaoEvento> optionalAvaliacaoEvento = avaliacaoEventoRepository.findById(id);
            return optionalAvaliacaoEvento.orElse(null);
        }

        public List<AvaliacaoEvento> getAll() {
            return avaliacaoEventoRepository.findAll();
        }

    @Override
    protected void validarDadosAvaliacao(AvaliacaoEventoDTO avaliacaoDto) {

    }
}
