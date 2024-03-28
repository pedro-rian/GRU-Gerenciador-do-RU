package br.com.ufrn.imd.gru.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Date;

@Entity
@Table(name = "cardapio")
public class Cardapio{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "prato_principal")
    private String pratoPrincipal;

    @Column(name = "acompanhamento")
    private String acompanhamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_refeicao")
    private TipoRefeicao tipoRefeicao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cardapio")
    private TipoCardapio tipoCardapio;

    @Column(name = "data")
    private Date data;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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