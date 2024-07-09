package br.com.ufrn.imd.gru.model;

import jakarta.persistence.*;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @Column(name = "autor", length = 100, nullable = false)
    private String autor;

}
