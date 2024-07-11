package br.com.ufrn.imd.gru.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "pessoaBiblioteca")
public class PessoaBiblioteca extends Pessoa{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name="generoPreferido", nullable = false)
    private String generoPreferido;

    @Column(name="qtdLivrosEmprestados")
    private int qtdLivrosEmprestados;

    @Column(name="qtdLivrosLidos")
    private int qtdLivrosLidos;

    @Column(name="qtdPaginasLidas")
    private int qtdPaginasLidas;

    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuario;

    public String getGeneroPreferido() {
        return generoPreferido;
    }

    public void setGeneroPreferido(String generoPreferido) {
        this.generoPreferido = generoPreferido;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getQtdPaginasLidas() {
        return qtdPaginasLidas;
    }

    public void setQtdPaginasLidas(int qtdPaginasLidas) {
        this.qtdPaginasLidas = qtdPaginasLidas;
    }

    public int getQtdLivrosEmprestados() {
        return qtdLivrosEmprestados;
    }

    public void setQtdLivrosEmprestados(int qtdLivrosEmprestados) {
        this.qtdLivrosEmprestados = qtdLivrosEmprestados;
    }

    public int getQtdLivrosLidos() {
        return qtdLivrosLidos;
    }

    public void setQtdLivrosLidos(int qtdLivrosLidos) {
        this.qtdLivrosLidos = qtdLivrosLidos;
    }
}
