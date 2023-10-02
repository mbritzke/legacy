package com.ilegra.ilegraTF.farmacia.pojo;

public class MedicamentoQuimio extends Medicamento {
    private boolean receita;

    public MedicamentoQuimio(String nome, String fabricante, String compostoPrincipal, boolean receita, double preco) {
        super(nome, fabricante, compostoPrincipal, preco);
        this.receita = receita;
    }

    public MedicamentoQuimio(String nome, String fabricante, String compostoPrincipal, String descricao,
                             boolean receita, double preco) {
        super(nome, fabricante, compostoPrincipal, descricao, preco);
        this.receita = receita;
    }

    public void setReceita(boolean novaReceita) {
        this.receita = novaReceita;
    }

    public boolean getReceita() {
        return receita;
    }

}

