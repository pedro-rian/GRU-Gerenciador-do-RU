package br.com.ufrn.imd.gru.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "consumidor")
public class Consumidor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToMany(mappedBy = "consumidor")
    private List<Assistencia> assistenciaList;

    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Assistencia> getAssistenciaList() {
        return assistenciaList;
    }

    public void setAssistenciaList(List<Assistencia> assistenciaList) {
        this.assistenciaList = assistenciaList;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
