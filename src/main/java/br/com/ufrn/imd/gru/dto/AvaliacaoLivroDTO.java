package br.com.ufrn.imd.gru.dto;

import br.com.ufrn.imd.gru.model.Livro;

public class AvaliacaoLivroDTO extends AvaliacaoDTO{
    private long id;
    private String tituloResenha;
    private String autorResenha;
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
