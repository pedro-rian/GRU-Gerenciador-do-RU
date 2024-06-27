package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.AvaliacaoDTO;

public abstract class AvaliacaoService<T extends AvaliacaoDTO> {

    public void criarAvaliacao(T avaliacaoDto) {
        // Common logic for creating an evaluation
        validarDadosAvaliacao(avaliacaoDto);
        // Further processing logic
    }

    protected abstract void validarDadosAvaliacao(T avaliacaoDto);
}
