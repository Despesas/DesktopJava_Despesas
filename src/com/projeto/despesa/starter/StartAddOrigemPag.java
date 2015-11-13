/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.starter;

import com.projeto.despesa.event.EventAddOrigem;

/**
 *
 * @author WILL
 */
public class StartAddOrigemPag extends EventAddOrigem {

    public StartAddOrigemPag(int parcela, int parcelas, String data, String obs, String descricao, String status, String valor) {
        super(parcela, parcelas, data, obs, descricao, status, valor);
    }
    
    
}
