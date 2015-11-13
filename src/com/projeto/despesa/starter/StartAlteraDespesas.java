/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.starter;

import com.projeto.despesa.event.EventAlteracaoDespesa;

/**
 *
 * @author uhitlei.barbosa
 */
public class StartAlteraDespesas extends EventAlteracaoDespesa {

    public StartAlteraDespesas(int parcela, int parcelas, String Data, String obs, String descricao, String status, String valor) {
        super(parcela, parcelas, Data, obs, descricao, status, valor);
    }

    public StartAlteraDespesas() {
    }

   
}
