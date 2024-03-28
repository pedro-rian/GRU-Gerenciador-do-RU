package br.com.ufrn.imd.gru.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "cardapio_convencional")
public class CardapioConvencional {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "prato_principal")
    private String pratoPrincipal;

    @Column(name = "acompanhamento")
    private String acompanhamento;

    @Column(name = "tipo_refeicao")
    private String tipoRefeicao;

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

    public String getTipoRefeicao() {
        return tipoRefeicao;
    }

    public void setTipoRefeicao(String tipoRefeicao) {
        this.tipoRefeicao = tipoRefeicao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
