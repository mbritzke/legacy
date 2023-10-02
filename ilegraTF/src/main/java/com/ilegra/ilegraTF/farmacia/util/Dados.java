package com.ilegra.ilegraTF.farmacia.util;

import com.ilegra.ilegraTF.farmacia.pojo.Cliente;
import com.ilegra.ilegraTF.farmacia.pojo.Medicamento;
import com.ilegra.ilegraTF.farmacia.pojo.MedicamentoQuimio;

public class Dados {

    public void inicializa(Cadastro cadastro){
        Cliente cliente1 = new Cliente("Sofia", 18, new CPF("414.312.454-88"));
        Cliente cliente2 = new Cliente("Roger", 65, new CPF("499.646.655-39"));

        cadastro.cadastroCliente(cliente1);
        cadastro.cadastroCliente(cliente2);

        Medicamento medicamento1 = new Medicamento("Tylenol", "Genérico", "paracetamol", 20);
        Medicamento medicamento2 = new Medicamento("Deslatoradia", "Bayer", "loratadina", "bom para a rinite", 15);

        Medicamento medicamento3 = new MedicamentoQuimio("Ritalina", "Bayer", "ervas", true, 55);
        Medicamento medicamento4 = new MedicamentoQuimio("Avamys","Glaxo", "fluorato", false, 15);

        cadastro.cadastraMedicamento(medicamento1);
        cadastro.cadastraMedicamento(medicamento2);
        cadastro.cadastraMedicamento(medicamento3);
        cadastro.cadastraMedicamento(medicamento4);
    }

}
