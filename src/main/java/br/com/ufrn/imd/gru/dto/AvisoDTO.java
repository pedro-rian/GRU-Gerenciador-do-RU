package br.com.ufrn.imd.gru.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AvisoDTO {
    private String titulo;
    private String descricao;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;

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
}
