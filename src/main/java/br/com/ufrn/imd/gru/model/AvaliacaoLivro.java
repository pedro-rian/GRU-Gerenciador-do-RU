package br.com.ufrn.imd.gru.model;

import jakarta.persistence.*;

@Entity
public class AvaliacaoLivro extends Avaliacao{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String tituloResenha;
    private String autorResenha;

    @ManyToOne(optional = true)
    @JoinColumn(name = "livro")
    private Livro livro;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTituloResenha() {
        return tituloResenha;
    }

    public void setTituloResenha(String tituloResenha) {
        this.tituloResenha = tituloResenha;
    }

    public String getAutorResenha() {
        return autorResenha;
    }

    public void setAutorResenha(String autorResenha) {
        this.autorResenha = autorResenha;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
