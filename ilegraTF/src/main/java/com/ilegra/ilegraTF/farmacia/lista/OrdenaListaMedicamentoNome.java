package com.ilegra.ilegraTF.farmacia.lista;


import com.ilegra.ilegraTF.farmacia.pojo.Medicamento;

import java.util.Comparator;

public class OrdenaListaMedicamentoNome implements Comparator<Medicamento>{

    @Override
    public int compare(Medicamento medicamento1, Medicamento medicamento2) {
        return medicamento1.getNome().compareTo(medicamento2.getNome());
    }
}
