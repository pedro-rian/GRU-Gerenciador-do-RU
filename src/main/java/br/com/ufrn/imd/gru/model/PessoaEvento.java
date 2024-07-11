package br.com.ufrn.imd.gru.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "pessoaEvento")
public class PessoaEvento extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private long id;

    @Column(name = "categoriaFavorita", nullable = false)
    private String categoriaFavorita;

    @Column(name="qtdEventosInscritos")
    private int qtdEventosInscritos;

    @Column(name="qtdEventosConcluidos")
    private int qtdEventosConcluidos;

    @Column(name="cargaHorariaTotal")
    private int cargaHorariaTotal;

    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuario;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoriaFavorita() {
        return categoriaFavorita;
    }

    public void setCategoriaFavorita(String categoriaFavorita) {
        this.categoriaFavorita = categoriaFavorita;
    }

    public int getQtdEventosInscritos() {
        return qtdEventosInscritos;
    }

    public void setQtdEventosInscritos(int qtdEventosInscritos) {
        this.qtdEventosInscritos = qtdEventosInscritos;
    }

    public int getQtdEventosConcluidos() {
        return qtdEventosConcluidos;
    }

    public void setQtdEventosConcluidos(int qtdEventosConcluidos) {
        this.qtdEventosConcluidos = qtdEventosConcluidos;
    }

    public int getCargaHorariaTotal() {
        return cargaHorariaTotal;
    }

    public void setCargaHorariaTotal(int cargaHorariaTotal) {
        this.cargaHorariaTotal = cargaHorariaTotal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
