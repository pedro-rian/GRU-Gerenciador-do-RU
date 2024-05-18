package br.com.ufrn.imd.gru.model;

import jakarta.persistence.*;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "quantidadeEstrelas", length = 100, nullable = false)
    private long quantidadeEstrelas;
    @Column(name = "descricao", length = 500, nullable = true)
    private String descricao;

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_cardapio")
    private Cardapio cardapio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getQuantidadeEstrelas() {
        return quantidadeEstrelas;
    }

    public void setQuantidadeEstrelas(long quantidadeEstrelas) {
        this.quantidadeEstrelas = quantidadeEstrelas;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }
}
