package br.com.ufrn.imd.gru.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @OneToMany
    private List<Assistencia> assistenciaList;

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
}
