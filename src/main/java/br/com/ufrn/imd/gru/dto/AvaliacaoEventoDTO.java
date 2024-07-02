package br.com.ufrn.imd.gru.dto;

import br.com.ufrn.imd.gru.model.Evento;

public class AvaliacaoEventoDTO extends AvaliacaoDTO{
    private long id;
    private long estrelasAcessibilidade;
    private long estrelasPontualidade;
    private long estrelasPalestrante;
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

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
