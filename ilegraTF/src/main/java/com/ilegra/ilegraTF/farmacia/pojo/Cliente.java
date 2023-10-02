package com.ilegra.ilegraTF.farmacia.pojo;

import com.ilegra.ilegraTF.farmacia.util.CPF;

public class Cliente {
    private String nome;
    private int idade;
    private CPF cpfCliente;
    private int pontos = 0;

    public Cliente(String nome, int idade, CPF cpfCliente) {
        this.nome = nome;
        this.idade = idade;
        this.cpfCliente = cpfCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public CPF getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(CPF cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public int getPontos(){
        return pontos;
    }

    public void setPontos(int pontos){
        this.pontos = pontos;
    }

    @Override
    public String toString() {
        return "CLIENTE NOME = " + nome + "\nIDADE = " + idade + "\n"  + cpfCliente;
    }
}
