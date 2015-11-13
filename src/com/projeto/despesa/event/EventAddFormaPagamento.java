/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.event;

import com.projeto.despesa.dao.Forma_pagamentoDAO;
import com.projeto.despesa.dao.OriundoDAO;
import com.projeto.despesa.dto.Oriundo;
import com.projeto.despesa.form.AddFormaPagamento;
import com.projeto.despesa.starter.StartDespesas;
import com.projeto.despesa.utilitarios.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import com.projeto.despesa.dto.Forma_pagamento;

/**
 *
 * @author uhitlei.barbosa
 */
public class EventAddFormaPagamento extends AddFormaPagamento implements ActionListener {

  public EventAddFormaPagamento() {
    // setLocationRelativeTo(null);
    setResizable(false);
    setVisible(true);
    this.btGravar.addActionListener(this);
    // this.setIconImage(new ImageIcon(this.getClass().getResource("/com/projeto/despesa/images/TributsIco32.png")).getImage());
  }

  private void salvar() {
    try {
      Forma_pagamento formaPagamento = new Forma_pagamento();
      formaPagamento.setData_cadastro(new Data().getDataBanco(ftfDataCadastro.getText()));
      formaPagamento.setNome(tfDescricao.getText());

      new Forma_pagamentoDAO().salvar(formaPagamento);
    } catch (SQLException ex) {
      Logger.getLogger(EventAddOrigem.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      this.dispose();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == this.btGravar) {
      {
        salvar();
      }

    }
  }
}
