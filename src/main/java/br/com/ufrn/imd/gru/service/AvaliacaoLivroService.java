package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.AvaliacaoLivroDTO;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoLivroService extends AvaliacaoService<AvaliacaoLivroDTO> {

    @Override
    protected void validarDadosAvaliacao(AvaliacaoLivroDTO avaliacaoDto) {
    }
}