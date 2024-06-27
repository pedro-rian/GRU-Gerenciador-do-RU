package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.AvaliacaoEventoDTO;
import br.com.ufrn.imd.gru.dto.AvaliacaoGRUDTO;
import br.com.ufrn.imd.gru.dto.AvaliacaoLivroDTO;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoEventoService extends AvaliacaoService<AvaliacaoEventoDTO> {

    @Override
    protected void validarDadosAvaliacao(AvaliacaoEventoDTO avaliacaoDto) {
        // Specific validation logic for Evento
    }
}


