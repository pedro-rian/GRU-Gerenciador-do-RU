package br.com.ufrn.imd.gru.service;

import br.com.ufrn.imd.gru.dto.AvaliacaoDTO;

import java.util.List;

public abstract class AbstractAvaliacaoService<T extends AvaliacaoDTO> implements AvaliacaoService<T> {

    @Override
    public void atualizar(long id, T avaliacaoDto) {
        validarDadosAvaliacao(avaliacaoDto);
        atualizarDados(id, avaliacaoDto);
    }

    protected abstract void atualizarDados(long id, T avaliacaoDto);

    @Override
    public void validarDadosAvaliacao(T avaliacaoDto) {
        if (avaliacaoDto == null) {
            throw new IllegalArgumentException("Avaliação não pode ser nula");
        }
    }

    protected abstract List<T> buscarAvaliacoesAtuais();

    public void cadastrar(T avaliacaoDto) {
        validarDadosAvaliacao(avaliacaoDto);
        salvar(avaliacaoDto);
    }
    protected abstract void salvar(T avaliacaoDto);

    @Override
    public void deleteById(long id) {
        excluirPorId(id);
    }

    protected abstract void excluirPorId(long id);
}
