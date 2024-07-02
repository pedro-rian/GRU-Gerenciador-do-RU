package br.com.ufrn.imd.gru.service;

public abstract class AvaliacaoService<T> {
    protected abstract void validarDadosAvaliacao(T avaliacaoDto);
}
