package com.ilegra.ilegraTF.farmacia.util;

import com.ilegra.ilegraTF.farmacia.pojo.Cliente;
import com.ilegra.ilegraTF.farmacia.pojo.Medicamento;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VendaTest {

    Cadastro novoCadastro;
    Venda vendaTeste;

    @Before
    public void inicializa(){
        novoCadastro = new Cadastro();
        vendaTeste = new Venda();
    }
}