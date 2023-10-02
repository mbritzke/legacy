package com.ilegra.ilegraTF.farmacia.util;

import com.ilegra.ilegraTF.farmacia.pojo.Cliente;
import com.ilegra.ilegraTF.farmacia.pojo.Medicamento;
import com.ilegra.ilegraTF.farmacia.pojo.MedicamentoQuimio;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RelatorioTest {

    List<Cliente> listaCliente;
    List<Medicamento> listaMedicamento;
    Relatorio relatorio;

    @Before
    public void inicializa(){
        listaCliente = new ArrayList<>();
        listaCliente.add(new Cliente("Pedro", 25, new CPF("452.753.914-04")));
        listaCliente.add(new Cliente("João", 18, new CPF("737.793.157-42")));
        listaCliente.add(new Cliente("Ana", 26, new CPF("448.175.083-96")));

        listaMedicamento = new ArrayList<>();
        listaMedicamento.add(new Medicamento("chá erva doce", "bayer", "ervas", 2.50));
        listaMedicamento.add(new Medicamento("dorflex", "generios", "mel", 5.0));
        listaMedicamento.add(new MedicamentoQuimio("chá preto", "bayer", "ervas", true, 1.80));

        relatorio = new Relatorio();
    }

    @Test
    public void ordenarClientePorNome(){
        List<Cliente> listaOrdenada = relatorio.listaClienteNome(listaCliente);
        Assert.assertEquals(listaOrdenada, listaCliente);
    }

    @Test
    public void ordenarMedicamentoNome(){
        List<Medicamento> listaOrdenada = relatorio.listaMedicamentoNome(listaMedicamento);
        Assert.assertEquals(listaOrdenada, listaMedicamento);
    }

    @Test
    public void listaMedicamentoFitoterapico(){
       List<Medicamento> somenteFitoterapico =  relatorio.fitoreapico(listaMedicamento);
       int tamanho = somenteFitoterapico.size();
       Assert.assertEquals(2, tamanho);
    }

    @Test
    public void listaMedicamentoQuimioterapico(){
        List<Medicamento> somenteQuimioterapico = relatorio.quimioterapico(listaMedicamento);
        Assert.assertEquals(1, somenteQuimioterapico.size());
    }
}