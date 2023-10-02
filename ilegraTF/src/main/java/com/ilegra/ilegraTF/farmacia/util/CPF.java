package com.ilegra.ilegraTF.farmacia.util;

public class CPF {
    private String cpf;
    private static final int[] pesoCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

    public CPF(String combinacao) {
        this.cpf = combinacao;
    }

    public String getCPF() {
        return cpf;
    }

    private int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public String limpaCPF(String cpf) {
        String c = cpf.replace("-", "").replace(".", "");
        return c;
    }

    public boolean validaCPF(String cpf) {
        String cleanCPF = limpaCPF(cpf);
        if ((cleanCPF == null) || (cleanCPF.length() != 11))
            return false;
        Integer digito1 = calcularDigito(cleanCPF.substring(0, 9), pesoCPF);
        Integer digito2 = calcularDigito(cleanCPF.substring(0, 9) + digito1, pesoCPF);
        return cleanCPF.equals(cleanCPF.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    @Override
    public String toString() {
        return "CPF: " + cpf + "\n";
    }
}
