/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.starter;

import com.projeto.despesa.event.EventCadastroDespesa;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author uhitlei.barbosa
 */
public class StartDespesas extends EventCadastroDespesa {

    public StartDespesas(int parcela, int parcelas, String Data, String obs, String descricao, String status, String valor) {
        super(parcela, parcelas, Data, obs, descricao, status, valor);
    }

    

    public StartDespesas() {
    }

   
}
