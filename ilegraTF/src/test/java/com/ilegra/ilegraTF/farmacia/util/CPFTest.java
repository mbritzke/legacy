package com.ilegra.ilegraTF.farmacia.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CPFTest {

    CPF cpfTest;

    @Before
    public void inicializa(){
        cpfTest = new CPF("273.234.709-43");
}

    @Test
    public void limpaCPFNormal(){
        String cpfComPontuacao = cpfTest.getCPF();
        Assert.assertEquals("27323470943", cpfTest.limpaCPF(cpfComPontuacao));
    }

    @Test
    public void limpaCPFBaguncado(){
        CPF novoCPF = new CPF("381.-.211..134---90");
        Assert.assertEquals("38121113490", novoCPF.limpaCPF(novoCPF.getCPF()));
    }

    @Test
    public void validaCPF(){
        Assert.assertTrue(cpfTest.validaCPF("857.644.785-10"));
    }

    @Test
    public void naoValidaCPF(){
        Assert.assertFalse(cpfTest.validaCPF("859.644.785-15"));
    }
}