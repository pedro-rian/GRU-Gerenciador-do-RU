package br.com.ufrn.imd.gru.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;
    @Column(name = "subtitulo", length = 500, nullable = true)
    private String descricao;
    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date data;
    @Column(name = "cargaHoraria")
    private int cargaHoraria;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getCargaHoraria() {return cargaHoraria;}

    public void setCargaHoraria(int cargaHoraria) {this.cargaHoraria = cargaHoraria;}
}
