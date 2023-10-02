package com.ilegra.ilegraTF.farmacia.util;

import com.ilegra.ilegraTF.farmacia.lista.OrdenaListaCliente;
import com.ilegra.ilegraTF.farmacia.lista.OrdenaListaMedicamentoNome;
import com.ilegra.ilegraTF.farmacia.pojo.Cliente;
import com.ilegra.ilegraTF.farmacia.pojo.Medicamento;
import com.ilegra.ilegraTF.farmacia.pojo.MedicamentoQuimio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Relatorio {

    private List<Medicamento> fitoterapico;
    private List<Medicamento> quimioterapico;

    public Relatorio(){
        fitoterapico = new ArrayList<>();
        quimioterapico = new ArrayList<>();
    }

    public List<Cliente> listaClienteNome(List<Cliente> listaClientes){
        OrdenaListaCliente ordena = new OrdenaListaCliente();
        Collections.sort(listaClientes, ordena);
        return listaClientes;
    }

    public List<Medicamento> listaMedicamentoNome(List<Medicamento> listaMedicamento){
        OrdenaListaMedicamentoNome ordena =  new OrdenaListaMedicamentoNome();
        Collections.sort(listaMedicamento, ordena);
        return listaMedicamento;
    }

    public List<Medicamento> fitoreapico(List<Medicamento> listaMedicamento){
        separaTipoMedicamento(listaMedicamento);
        return fitoterapico;
    }

    private void separaTipoMedicamento(List<Medicamento> listaMedicamento){
        for(Medicamento auxiliar: listaMedicamento){
            if(auxiliar instanceof MedicamentoQuimio)
               quimioterapico.add(auxiliar);
            else
                fitoterapico.add(auxiliar);
        }
    }

   public List<Medicamento> quimioterapico(List<Medicamento> listaMedicamento){
        separaTipoMedicamento(listaMedicamento);
        return quimioterapico;
   }
}
