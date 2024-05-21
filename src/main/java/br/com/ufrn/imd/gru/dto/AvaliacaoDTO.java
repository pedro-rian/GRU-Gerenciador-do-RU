package br.com.ufrn.imd.gru.dto;

import br.com.ufrn.imd.gru.model.Cardapio;
import br.com.ufrn.imd.gru.model.TipoCardapio;
import br.com.ufrn.imd.gru.model.TipoRefeicao;

import java.util.Date;

public class AvaliacaoDTO {
    private long quantidadeEstrelas;
    private String descricao;
    private TipoRefeicao tipoRefeicao;
    private TipoCardapio tipoCardapio;
    private Date date;

    private Cardapio cardapio;

    public long getQuantidadeEstrelas() {
        return quantidadeEstrelas;
    }

    public void setQuantidadeEstrelas(long quantidadeEstrelas) {
        this.quantidadeEstrelas = quantidadeEstrelas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoRefeicao getTipoRefeicao() {
        return tipoRefeicao;
    }

    public void setTipoRefeicao(TipoRefeicao tipoRefeicao) {
        this.tipoRefeicao = tipoRefeicao;
    }

    public TipoCardapio getTipoCardapio() {
        return tipoCardapio;
    }

    public void setTipoCardapio(TipoCardapio tipoCardapio) {
        this.tipoCardapio = tipoCardapio;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }
}
