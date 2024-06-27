package br.com.ufrn.imd.gru.model;

import jakarta.persistence.*;

@Entity
public class AvaliacaoEvento extends Avaliacao{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private long estrelasAcessibilidade;
    private long estrelasPontualidade;
    private long estrelasPalestrante;

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_evento")
    private Evento evento;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEstrelasAcessibilidade() {
        return estrelasAcessibilidade;
    }

    public void setEstrelasAcessibilidade(long estrelasAcessibilidade) {
        this.estrelasAcessibilidade = estrelasAcessibilidade;
    }

    public long getEstrelasPontualidade() {
        return estrelasPontualidade;
    }

    public void setEstrelasPontualidade(long estrelasPontualidade) {
        this.estrelasPontualidade = estrelasPontualidade;
    }

    public long getEstrelasPalestrante() {
        return estrelasPalestrante;
    }

    public void setEstrelasPalestrante(long estrelasPalestrante) {
        this.estrelasPalestrante = estrelasPalestrante;
    }
}
