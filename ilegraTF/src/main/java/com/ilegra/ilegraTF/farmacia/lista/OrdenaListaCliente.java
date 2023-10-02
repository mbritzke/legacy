package com.ilegra.ilegraTF.farmacia.lista;


import com.ilegra.ilegraTF.farmacia.pojo.Cliente;

import java.util.Comparator;

public class OrdenaListaCliente implements Comparator<Cliente> {

    @Override
    public int compare(Cliente cliente1, Cliente cliente2) {
        return cliente1.getNome().compareTo(cliente2.getNome());
    }
}
