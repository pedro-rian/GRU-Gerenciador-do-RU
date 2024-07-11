package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.AvaliacaoDTO;


public interface AvaliacaoService<T extends AvaliacaoDTO> {
    T getById(long id);
    void validarDadosAvaliacao(T avaliacaoDto);
    void atualizar(long id, T avaliacao);
    void deleteById(long id);
}
