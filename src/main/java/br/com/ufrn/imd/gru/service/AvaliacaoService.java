package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.AvaliacaoDTO;
import br.com.ufrn.imd.gru.model.AvaliacaoGRU;

import java.util.List;

public interface AvaliacaoService<T extends AvaliacaoDTO> {
    T getById(long id);
    void validarDadosAvaliacao(T avaliacaoDto);
    AvaliacaoGRU cadastrar(T avaliacao);
    void atualizar(long id, T avaliacao);

    List<AvaliacaoGRU> getAvaliacoesAtuais();

    void deleteById(long id);
}
