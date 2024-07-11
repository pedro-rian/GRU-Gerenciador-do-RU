package br.com.ufrn.imd.gru.dto;

import br.com.ufrn.imd.gru.model.TipoUsuario;

public class PessoaGruDTO extends PessoaDTO {
    private int idade;
    private double peso;
    private double altura;

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

}


