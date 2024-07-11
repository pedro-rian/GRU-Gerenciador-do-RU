package br.com.ufrn.imd.gru.dto;

import br.com.ufrn.imd.gru.model.TipoUsuario;

public class PessoaBibliotecaDTO extends PessoaDTO {

    private String generoPreferido;
    private int qtdLivrosEmprestados;
    private int qtdLivrosLidos;
    private int qtdPaginasLidas;

    public String getGeneroPreferido() {
        return generoPreferido;
    }

    public void setGeneroPreferido(String generoPreferido) {
        this.generoPreferido = generoPreferido;
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

    public int getQtdPaginasLidas() {
        return qtdPaginasLidas;
    }

    public void setQtdPaginasLidas(int qtdPaginasLidas) {
        this.qtdPaginasLidas = qtdPaginasLidas;
    }
}


