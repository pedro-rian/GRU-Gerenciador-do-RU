package br.com.ufrn.imd.gru.model;

import jakarta.persistence.*;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;
    @Column(name = "descricao", length = 500, nullable = true)
    private String descricao;
    @Column(name = "data", nullable = false)
    private String data;
}
