package br.com.ufrn.imd.gru;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Usuario {

    @Id
    private long id;

    private String nome;

    private String email;

}
