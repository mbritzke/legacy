package com.ilegra.ilegraTF.farmacia.usuario;

import com.ilegra.ilegraTF.farmacia.util.Dados;

public class App {

    public static void main(String args[]){
        InterfaceGrafica menu = new InterfaceGrafica();
        Dados dados = new Dados();
        dados.inicializa(menu.retornaCadastro());
        menu.interacao();
    }
}
