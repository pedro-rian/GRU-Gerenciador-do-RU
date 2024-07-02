package br.com.ufrn.imd.gru.dto;

import br.com.ufrn.imd.gru.model.Cardapio;
import br.com.ufrn.imd.gru.model.TipoCardapio;
import br.com.ufrn.imd.gru.model.TipoRefeicao;

import java.util.Date;

public class AvaliacaoGRUDTO extends AvaliacaoDTO{
    private long id;
    private long quantidadeEstrelas;
    private TipoRefeicao tipoRefeicao;
    private TipoCardapio tipoCardapio;
    private Date date;

    private Cardapio cardapio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuantidadeEstrelas() {
        return quantidadeEstrelas;
    }

    public void setQuantidadeEstrelas(long quantidadeEstrelas) {
        this.quantidadeEstrelas = quantidadeEstrelas;
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
