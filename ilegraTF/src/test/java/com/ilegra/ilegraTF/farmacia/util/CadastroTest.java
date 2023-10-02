package com.ilegra.ilegraTF.farmacia.util;

import com.ilegra.ilegraTF.farmacia.pojo.Cliente;
import com.ilegra.ilegraTF.farmacia.pojo.Medicamento;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CadastroTest {

    Cadastro novoCadastro = new Cadastro();

    @Test
    public void cadastroCliente() {
        Cliente novoCliente = new Cliente("João", 18, new CPF("195.864.874-40"));
        Assert.assertTrue(novoCadastro.cadastroCliente(novoCliente));
    }

    @Test
    public void cadastroClienteRepetido() {
        Cliente novoCliente = new Cliente("Laura", 76, new CPF("445.161.923-81"));
        Assert.assertTrue(novoCadastro.cadastroCliente(novoCliente));
        Assert.assertFalse(novoCadastro.cadastroCliente(novoCliente));
    }

    @Test
    public void buscaCliente(){
        Cliente clienteAlvo = new Cliente("Maria", 45, new CPF("753.758.644-60"));
        novoCadastro.cadastroCliente(clienteAlvo);
        Assert.assertTrue(novoCadastro.pesquisaCliente(new CPF("753.758.644-60")));
    }

    @Test
    public void naoEncontrarCliente(){
        Assert.assertFalse(novoCadastro.pesquisaCliente(new CPF("493.203.845-33")));
    }

    @Test
    public void listaClientesVazia(){
        List<Cliente> listaClienteEsperada = new ArrayList<Cliente>();
        List<Cliente> listaClienteAtual = novoCadastro.retornaListaClientes();
        Assert.assertEquals(listaClienteEsperada.size(), listaClienteAtual.size());
    }

    @Test
    public void listaClientesComDados(){
        List<Cliente> listaClienteEsperada = new ArrayList<Cliente>();
        List<Cliente> listaClienteAtual = novoCadastro.retornaListaClientes();
        Cliente novoBase = new Cliente("Laura", 76, new CPF("445.161.923-81"));
        listaClienteAtual.add(novoBase);
        listaClienteEsperada.add(novoBase);
        Assert.assertSame(listaClienteAtual.size(), listaClienteEsperada.size());
    }

    @Test
    public void cadastraMedicamento(){
        Medicamento novoMedicamento = new Medicamento("Bepantol", "Derma Creme", "Dexpantenol", 20.0);
        Assert.assertTrue(novoCadastro.cadastraMedicamento(novoMedicamento));
    }

    @Test
    public void cadastaMedicamentoRepetido(){
        Medicamento novoMedicamento = new Medicamento("Nebacetin", "Generico", "Sulfato De Neomicina", 15.0);
        Assert.assertTrue(novoCadastro.cadastraMedicamento(novoMedicamento));
        Assert.assertFalse(novoCadastro.cadastraMedicamento(novoMedicamento));
    }

    @Test
    public void pesquisaMedicamento(){
        Medicamento medicamentoAlvo = new Medicamento("Aspirina", "Bayer", "Acido acetilsalicilico", 10.0);
        novoCadastro.cadastraMedicamento(medicamentoAlvo);
        Assert.assertTrue(novoCadastro.pesquisaMedicamento(medicamentoAlvo));
    }

    @Test
    public void naoEncontarMedicamento(){
        Assert.assertFalse(novoCadastro.pesquisaMedicamento(new Medicamento("Bala Valda", "generico", "menta", 14.0)));
    }

    @Test
    public void listaMedicamentoVazia(){
        List<Medicamento> listaAtual = new ArrayList<>();
        List<Medicamento> listaEsperada = new ArrayList<>();
        Assert.assertEquals(listaAtual.size(), listaEsperada.size());
    }

    @Test
    public void listaMedicamentoComDados(){
        List<Medicamento> listaAtual = new ArrayList<>();
        List<Medicamento> listaEsperada = new ArrayList<>();
        Medicamento medicamentoBase = new Medicamento("Aspirina", "Bayer", "Acido acetilsalicilico", 15.0);
        listaAtual.add(medicamentoBase);
        listaEsperada.add(medicamentoBase);
        Assert.assertEquals(listaAtual.size(), listaEsperada.size());
    }
}