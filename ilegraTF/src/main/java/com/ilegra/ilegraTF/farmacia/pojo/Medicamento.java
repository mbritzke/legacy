package com.ilegra.ilegraTF.farmacia.pojo;

public class Medicamento {
    private String nome;
    private String fabricante;
    private String compostoPrincipal;
    private String descricao;
    private double preco;

    public Medicamento(String nome, String fabricante, String compostoPrincipal, double preco) {
        this.nome = nome;
        this.fabricante = fabricante;
        this.compostoPrincipal = compostoPrincipal;
        this.preco = preco;
    }

    public Medicamento(String nome, String fabricante, String compostoPrincipal, String descricao, double preco) {
        this(nome, fabricante, compostoPrincipal, preco);
        this.descricao = descricao;
    }

    public String getNome() {
        return this.nome;
    }

    public String getFabricante() {
        return this.fabricante;
    }

    public String getCompostoPrincipal() {
        return this.compostoPrincipal;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public double getPreco(){return this.preco;}

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public void setCompostoPrincipal(String compostoPrincipal) {
        this.compostoPrincipal = compostoPrincipal;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "NOME: " + nome + "\nFABRICANTE: " + "\nCOMPOSTO PRINCIPAL: " + compostoPrincipal + "\nDESCRICAO: "
                + descricao;
    }
}
