package com.ilegra.ilegraTF.farmacia.util;

import com.ilegra.ilegraTF.farmacia.pojo.Medicamento;
import com.ilegra.ilegraTF.farmacia.pojo.MedicamentoQuimio;

import java.util.ArrayList;
import java.util.List;

public class Venda {

    private List<Medicamento> listaCompras;

    public Venda(){
        listaCompras = new ArrayList<>();
    }

    public List<Medicamento> retornaListaCompras(){
        return listaCompras;
    }

    public boolean listaCompras(Medicamento produto){
        List<Medicamento> listaCompras = new ArrayList<>();
        listaCompras.add(produto);
        return true;
    }

    public double calculaVenda(int idadeCliente){
        double valorCompra = 0;
        for(Medicamento auxiliar: listaCompras){
            valorCompra += auxiliar.getPreco();
        }
        if(idadeCliente > 60)
            return valorCompra * 0.20;
        return valorCompra;
    }

    public int calculaPontos(double valor){
        int contador = 0;
        while(valor > 9){
            valor -= 10;
            contador++;
        }
        return contador*5;
    }

    public double vende(double valorTotal, double pagamento){
        double troco = pagamento - valorTotal;
        return troco;
    }

    public List<Medicamento> medicamentosComReceita(){
        List<Medicamento> medicamentosComReceita = new ArrayList<>();
        for(Medicamento aux: listaCompras){
            if(aux instanceof MedicamentoQuimio)
                medicamentosComReceita.add(aux);
        }
        return medicamentosComReceita;
    }
}
