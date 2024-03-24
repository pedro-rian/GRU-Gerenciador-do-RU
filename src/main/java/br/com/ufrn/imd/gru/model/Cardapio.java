package br.com.ufrn.imd.gru.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    private List<Refeicao> pratosPrincipais;

    @OneToMany
    private List<Refeicao> acompanhamentos;

    @OneToMany
    private List<Refeicao> vegetarianos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Refeicao> getPratosPrincipais() {
        return pratosPrincipais;
    }

    public void setPratosPrincipais(List<Refeicao> pratosPrincipais) {
        this.pratosPrincipais = pratosPrincipais;
    }

    public List<Refeicao> getAcompanhamentos() {
        return acompanhamentos;
    }

    public void setAcompanhamentos(List<Refeicao> acompanhamentos) {
        this.acompanhamentos = acompanhamentos;
    }

    public List<Refeicao> getVegetarianos() {
        return vegetarianos;
    }

    public void setVegetarianos(List<Refeicao> vegetarianos) {
        this.vegetarianos = vegetarianos;
    }

}
