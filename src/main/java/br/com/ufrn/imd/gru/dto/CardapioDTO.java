package br.com.ufrn.imd.gru.dto;

import br.com.ufrn.imd.gru.model.TipoCardapio;
import br.com.ufrn.imd.gru.model.TipoRefeicao;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CardapioDTO {

    private TipoCardapio tipoCardapio;
    private TipoRefeicao tipoRefeicao;
    private String pratoPrincipal;
    private String acompanhamento;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;

    // Getters and setters
    public String getPratoPrincipal() {
        return pratoPrincipal;
    }

    public void setPratoPrincipal(String pratoPrincipal) {
        this.pratoPrincipal = pratoPrincipal;
    }

    public String getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(String acompanhamento) {
        this.acompanhamento = acompanhamento;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}

