package br.com.ufrn.imd.gru.dto;

import br.com.ufrn.imd.gru.model.TipoUsuario;

public class PessoaEventoDTO extends PessoaDTO {
    private String categoriaFavorita;
    private int qtdEventosInscritos;
    private int qtdEventosConcluidos;
    private int cargaHorariaTotal;

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
}


