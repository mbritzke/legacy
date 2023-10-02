package com.ilegra.ilegraTF.farmacia.usuario;

import com.ilegra.ilegraTF.farmacia.pojo.Cliente;
import com.ilegra.ilegraTF.farmacia.pojo.Medicamento;
import com.ilegra.ilegraTF.farmacia.pojo.MedicamentoQuimio;
import com.ilegra.ilegraTF.farmacia.util.*;

import java.util.List;
import java.util.Scanner;

public class InterfaceGrafica {

    private CPF cpfAuxiliar;
    private Cadastro cadastro;
    private Relatorio relatorio;
    private Scanner teclado;
    private final String fitoreatpico = "FITOTERAPICO";
    private final String quimioterapico = "QUIMIOTERAPICO";
    private final String padrao = "(1) - SIM\n(2) - NÃO";

    public InterfaceGrafica(){
        cpfAuxiliar = new CPF("000000000");
        cadastro  = new Cadastro();
        teclado = new Scanner(System.in);
        relatorio = new Relatorio();
    }

    public Cadastro retornaCadastro(){
        return cadastro;
    }

    public void interacao(){
        System.out.println("    ______    ___     ____     __  ___    ___    ______    ____    ___ \n" +
                "   / ____/   /   |   / __ \\   /  |/  /   /   |  / ____/   /  _/   /   |\n" +
                "  / /_      / /| |  / /_/ /  / /|_/ /   / /| | / /        / /    / /| |\n" +
                " / __/     / ___ | / _, _/  / /  / /   / ___ |/ /___    _/ /    / ___ |\n" +
                "/_/       /_/  |_|/_/ |_|  /_/  /_/   /_/  |_|\\____/   /___/   /_/  |_|\n" +
                "                                                                               ");
        System.out.println(" - Escolha uma abaixo:\n" +
                "(1) Cadastrar usuário\n" +
                "(2) Cadastrar medicamento\n" +
                "(3) Venda\n" +
                "(4) Relátorios\n" +
                "(5) Sair\n");
        int escolha = teclado.nextInt();
        switch (escolha){
            case 1:
                String situacaoCliente = interacaoCadastraUsuario();
                System.out.println(situacaoCliente);
                break;
            case 2:
                String statusMedicamento = interacaoMedicamento();
                System.out.println(statusMedicamento);
                break;
            case 3:
                String statusVenda = interacaoVenda();
                System.out.println(statusVenda);
                break;
            case 4:
                String statusRelatorio = interacaoRelatorio();
                System.out.println(statusRelatorio);
                break;
            case 5:
                System.out.println("tchau");
                teclado.close();
                System.exit(0);
        }
        teclado.close();
    }

    private String interacaoCadastraUsuario(){
        System.out.println("1 - CADASTRAR CLIENTE: \n" +
                "Digite o CPF: ");
        String cpf = teclado.next();
        CPF cpfNovoCliente = new CPF(cpf);
        Cliente auxiliar = cadastro.pesquisaRetornaCliente(cpfNovoCliente);
        if(auxiliar.getIdade() != 0){
            System.out.println("Digite o nome: ");
            String nome = teclado.next();
            System.out.println("Digite a idade: ");
            int idade = teclado.nextInt();
            Cliente novoCliente = new Cliente(nome, idade, cpfNovoCliente);
            return cadastraUsuario(novoCliente);
        }
        return "CLIENTE JÁ CADASTRADO!";
    }

    private String cadastraUsuario(Cliente novoCliente){
        boolean statusCLiente = cadastro.cadastroCliente(novoCliente);
        if(statusCLiente)
            return"CLIENTE CADASTRADO!";
        return "CLIENTE JÁ CADASTRADO!";
    }

    private String interacaoMedicamento() {
        System.out.println("  __  __   ______   _____    _____    _____              __  __   ______   _   _   _______    ____  \n" +
                " |  \\/  | |  ____| |  __ \\  |_   _|  / ____|     /\\     |  \\/  | |  ____| | \\ | | |__   __|  / __ \\ \n" +
                " | \\  / | | |__    | |  | |   | |   | |         /  \\    | \\  / | | |__    |  \\| |    | |    | |  | |\n" +
                " | |\\/| | |  __|   | |  | |   | |   | |        / /\\ \\   | |\\/| | |  __|   | . ` |    | |    | |  | |\n" +
                " | |  | | | |____  | |__| |  _| |_  | |____   / ____ \\  | |  | | | |____  | |\\  |    | |    | |__| |\n" +
                " |_|  |_| |______| |_____/  |_____|  \\_____| /_/    \\_\\ |_|  |_| |______| |_| \\_|    |_|     \\____/ \n" +
                "                                                                                                    \n" +
                "Selecione o tipo:\n" +
                "(1) - Fitoterapico\n" +
                "(2) - Quimioterapico\n");
        int tipoMedicamento = teclado.nextInt();
        if (tipoMedicamento == 1)
            System.out.println("MEDICAMENTO " + fitoreatpico);
        else
            System.out.println("MEDICAMENTO: " + quimioterapico);
        return pesquisaMedicamento(tipoMedicamento);
    }

    private String pesquisaMedicamento(int tipoMedicamento) {
        System.out.println("Digite o nome: ");
        String nome = teclado.next();
        Medicamento medicamento = cadastro.pesquisaRetornaMedicamento(nome);
        if (!medicamento.getNome().equalsIgnoreCase("vazio"))
            return "MEDICAMENTO JÁ CADASTRADO!";
        return insereDadosMedicamento(tipoMedicamento, nome);
    }

    private String insereDadosMedicamento(int tipoMedicamento, String nome){
        System.out.println("Digite o fabricante: ");
        String fabricante = teclado.next();
        System.out.println("Digite o composto principal: ");
        String compostoPrincipal = teclado.next();
        System.out.println("Digite o preço: ");
        double preco = teclado.nextDouble();
        System.out.println("Medicamento precisa de descrição? \n" + padrao);
        int escolha = teclado.nextInt();
        String descricao = null;
        if(escolha == 1){
            System.out.println("Digite a descriçao: ");
            descricao = teclado.next();
        }
        boolean status;
        if(tipoMedicamento == 1){
            if(descricao == null){
                Medicamento novoMedicamento = new Medicamento(nome, fabricante, compostoPrincipal, preco);
                status = cadastro.cadastraMedicamento(novoMedicamento);
            }else{
                Medicamento novoMedicamento = new Medicamento(nome, fabricante, compostoPrincipal, descricao ,preco);
                status = cadastro.cadastraMedicamento(novoMedicamento);
            }
        }
        else{
            boolean receita = false;
            System.out.println("Medicamento precisa de receita? "+ "\n" + padrao);
            int resposta = teclado.nextInt();
            if(resposta == 1){
                receita = true;
            }
            if(descricao == null){
                Medicamento novoMedicamento = new MedicamentoQuimio(nome, fabricante, compostoPrincipal, receita, preco);
                status = cadastro.cadastraMedicamento(novoMedicamento);
            }else{
                Medicamento novoMedicamento = new MedicamentoQuimio(nome, fabricante, compostoPrincipal, descricao,receita, preco);
                status = cadastro.cadastraMedicamento(novoMedicamento);
            }
        }
        if(!status)
            return "MEDICAMENTO JÁ CADASTRADO";
        return "MEDICAMENTO CADASTRADO";
    }

    private String interacaoVenda() {
        System.out.println("Digite o CPF do cliente: ");
        String cpf = teclado.next();
        boolean statusCPF = cpfAuxiliar.validaCPF(cpf);
        if(!statusCPF)
            return "CPF NÃO É VALIDO";
        Cliente comprador = cadastro.pesquisaRetornaCliente(new CPF(cpf));
        if(comprador.getNome() == null)
            return "CLIENTE NÃO CADASTRADO";
        return criaListaCompras(comprador);
    }

    private String criaListaCompras(Cliente comprador) {
        Venda novaVenda = new Venda();
        System.out.println("=== ATENÇÃO ===\n" +
                "Medicamentos Quimioterápicos precisam de receita para serem vendidos");
        boolean terminou = false;
        while(!terminou){
            System.out.println("Digite o medicamento: ");
            String medicamento = teclado.next();
            Medicamento produto = cadastro.pesquisaRetornaMedicamento(medicamento);
            if(produto.getNome() != null)
                novaVenda.listaCompras(produto);
            else
                System.out.println("Medicamento inválido");
            System.out.println("Deseja adicionar mais um produto?" + "\n" + padrao);
            int escolha = teclado.nextInt();
            if(escolha == 1)
               terminou = true;
        }
        return listaCompraMedicamentoQuimio(comprador, novaVenda);
    }

    private String listaCompraMedicamentoQuimio(Cliente comprador, Venda novaVenda){
        List<Medicamento> medicamentosComReceita = novaVenda.medicamentosComReceita();
        if(medicamentosComReceita != null){
            System.out.println("Medicamentos precisam de receita");
            System.out.println("(1) - Cliente tem a receita");
            System.out.println("(2) - Cliente não tem receita");
            int statusReceita = teclado.nextInt();
            if(statusReceita == 2)
                return "VENDA CANCELADA, CLIENTE NÃO POSSUI RECEITA";
        }
        return precoCompra(comprador, novaVenda);
    }

    private String precoCompra(Cliente comprador, Venda novaVenda){
        double total = novaVenda.calculaVenda(comprador.getIdade());
        System.out.println("Total de produtos: " + total);
        if (comprador.getPontos() >= total) {
            System.out.println("CLIENTE: " + comprador.getNome() + " tem " + comprador.getPontos() + " pontos");
            System.out.println("Deseja pagar com pontos? " + "\n" + padrao);
            int escolhePontos = teclado.nextInt();
            if (escolhePontos == 1) {
                comprador.setPontos(0);
            }
        } else {
            System.out.println("Digite o valor: ");
            double pagamento = teclado.nextDouble();
            System.out.println("TROCO = " + novaVenda.vende(total, pagamento));
            comprador.setPontos(novaVenda.calculaPontos(pagamento));
            System.out.println(comprador.toString());
        }
        return "VENDA REALIZADA";
    }

    private String interacaoRelatorio(){
        System.out.println("  _____    ______   _                   _______     __    _____    _____    ____     _____ \n" +
                " |  __ \\  |  ____| | |          /\\     |__   __|   /_/   |  __ \\  |_   _|  / __ \\   / ____|\n" +
                " | |__) | | |__    | |         /  \\       | |     / _ \\  | |__) |   | |   | |  | | | (___  \n" +
                " |  _  /  |  __|   | |        / /\\ \\      | |    | | | | |  _  /    | |   | |  | |  \\___ \\ \n" +
                " | | \\ \\  | |____  | |____   / ____ \\     | |    | |_| | | | \\ \\   _| |_  | |__| |  ____) |\n" +
                " |_|  \\_\\ |______| |______| /_/    \\_\\    |_|     \\___/  |_|  \\_\\ |_____|  \\____/  |_____/ " +
                "Escolha uma opção abaixo:\n" +
                "(1) - Clientes por ordem alfabetica\n" +
                "(2) - Medicamentos por ordem alfabetica\n" +
                "(3) - Medicamentos por tipo\n");
        int escolha = teclado.nextInt();
        return escolheRelatorio(escolha);
    }

    private String escolheRelatorio(int escolha) {
        if(escolha == 1) {
            List<Cliente> listaCliente = relatorio.listaClienteNome(cadastro.retornaListaClientes());
            return imprimeRelatorioCliente(listaCliente);
        }else if(escolha == 2){
            List<Medicamento> listaMedicamento =  relatorio.listaMedicamentoNome(cadastro.retornaListaMedicamentos());
            return imprimeRelatorioMedicamento(listaMedicamento);
        }else{
            return escolheTipoMedicamento();
        }
    }

    private String escolheTipoMedicamento() {
        System.out.println("Escolha o tipo:\n" +
                "(1) - Fitoterapico\n" +
                "(2) - Quimioterapico");
        int escolhaTipo = teclado.nextInt();
        if(escolhaTipo == 1)
            return imprimeFitoterapico();
        return imprimeQuimioTerapico();
    }


    private String imprimeFitoterapico() {
        List<Medicamento> fitoterapico = relatorio.fitoreapico(cadastro.retornaListaMedicamentos());
        return imprimeRelatorioMedicamento(fitoterapico);
    }

    private String imprimeQuimioTerapico() {
        List<Medicamento> quimiotterapico = relatorio.quimioterapico(cadastro.retornaListaMedicamentos());
        return imprimeRelatorioMedicamento(quimiotterapico);
    }

    private String imprimeRelatorioCliente(List<Cliente> listaCliente) {
        for(Cliente cliente: listaCliente){
            System.out.println(cliente.toString());
        }
        return "FIM DA LISTA";
    }

    private String imprimeRelatorioMedicamento(List<Medicamento> listaMedicamento) {
        for(Medicamento medicamento: listaMedicamento){
            System.out.println(medicamento.toString());
        }
        return "FIM DA LISTA";
    }

}

