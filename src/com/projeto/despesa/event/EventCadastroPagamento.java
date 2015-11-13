/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.event;

import com.projeto.despesa.form.CadastroPagamento;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import com.projeto.despesa.dao.DespesasDAO;
import com.projeto.despesa.dao.Forma_pagamentoDAO;
import com.projeto.despesa.dao.OriundoDAO;
import com.projeto.despesa.dto.Forma_pagamento;
import com.projeto.despesa.dto.Oriundo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author uhitlei.barbosa
 */
public class EventCadastroPagamento extends CadastroPagamento implements ActionListener {

  EventAddFormaPagamento addFormaPagamento = null;
  /**
   * ***************DATA*************
   */
  Date dt1 = new Date();
  GregorianCalendar cal = new GregorianCalendar();
  /**
   * ***************DATA*************
   */
  DespesasDAO desp;
  List<String> ano;
  List<Oriundo> ori;
  List<Forma_pagamento> fPag;

  public EventCadastroPagamento() {

    getInit();
  }

  private boolean getInit() {
    try {
      this.desp = new DespesasDAO();
      this.ori = getEncheListOrigem();
      this.fPag = new Forma_pagamentoDAO().listar();
      ano = desp.listAnoByDespesas();

      this.panePagamentoPrinc.bt_addFormaPagamento.addActionListener(this);
      this.panePagamentoPrinc.bt_refreshComboPagamento.addActionListener(this);


      cal.setTime(dt1);



      setLocationRelativeTo(null);
      setResizable(false);
      setVisible(true);
      this.setIconImage(new ImageIcon(this.getClass().getResource("/com/projeto/despesa/images/TributsIco32.png")).getImage());

      encheComboBoxAno(this.panePagamentoPrinc.cb_ano);
      encheComboBoxOrigem(this.panePagamentoPrinc.cb_oriundo);
      encheComboBoxFormaPagamento(this.panePagamentoPrinc.cbFormaPagamento);


      setDataAtual(this.panePagamentoPrinc.cb_ano, this.panePagamentoPrinc.cb_mes);
      //this.panePagamentoPrinc.cb_mes.setSelectedIndex(cal.get(Calendar.MONTH) + 1);

      return true;
    } catch (SQLException ex) {
      Logger.getLogger(EventCadastroPagamento.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
  }

  private void encheComboBoxAno(JComboBox cb_ano) {
    try {

      DefaultComboBoxModel defaultComboBox = new DefaultComboBoxModel(this.ano.toArray());
      cb_ano.setModel(defaultComboBox);

    } catch (Exception ex) {
      Logger.getLogger(EventCadastroPagamento.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  private void encheComboBoxOrigem(JComboBox cb_origem) {
    try {

      List<String> oriundo = new ArrayList<String>();
      for (int i = 0; i < ori.size(); i++) {
        oriundo.add(ori.get(i).getDescricao());
      }
      DefaultComboBoxModel defaultComboBox = new DefaultComboBoxModel(oriundo.toArray());
      cb_origem.setModel(defaultComboBox);
    } catch (Exception ex) {
      Logger.getLogger(EventCadastroPagamento.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  private void encheComboBoxFormaPagamento(JComboBox cb_formaPagamento) {
    try {
      cb_formaPagamento.removeAllItems();
      if (fPag != null) {
        List<String> fpagmnt = new ArrayList<String>();
        for (int i = 0; i < fPag.size(); i++) {
          fpagmnt.add(fPag.get(i).getNome());
        }
        DefaultComboBoxModel defaultComboBox = new DefaultComboBoxModel(fpagmnt.toArray());
        cb_formaPagamento.setModel(defaultComboBox);
      } else {
        cb_formaPagamento.addItem("Cadastrar F. Pagamento");
      }
    } catch (Exception ex) {
      Logger.getLogger(EventCadastroPagamento.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  private List<Oriundo> getEncheListOrigem() {
    List<Oriundo> list = null;
    try {
      list = new OriundoDAO().listar();
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(this, "SQLException: " + ex.getMessage(), "Mensagem do Sistema - ListarDespesas", 0);
    } catch (NullPointerException ex) {
      JOptionPane.showMessageDialog(this, "NullPointerException: " + ex.getMessage(), "Mensagem do Sistema - ListarDespesas", 0);
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this, "Exception: " + ex.getMessage(), "Mensagem do Sistema - ListarDespesas", 0);
    }

    return list;
  }

  private void setDataAtual(JComboBox cb_ano, JComboBox cb_mes) {
    try {
      int ano = cal.get(Calendar.YEAR);
      int mes = cal.get(Calendar.MONTH) + 1;

      if (mes == 12) {
        mes = 1;
        ano++;
      } else {
        mes++;
      }

      for (int i = 0; i < this.ano.size(); i++) {
        if (ano == Integer.parseInt(this.ano.get(i))) {
          cb_ano.setSelectedIndex(i);
        }
      }
      cb_mes.setSelectedIndex(mes);
    } catch (Exception ex) {
      Logger.getLogger(EventCadastroPagamento.class.getName()).log(Level.SEVERE, null, ex);
    }


  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == this.panePagamentoPrinc.bt_addFormaPagamento) {
      if (addFormaPagamento == null) {
        addFormaPagamento = new EventAddFormaPagamento();
        this.remove(panePagamentoPrinc);
        this.add(addFormaPagamento);
        this.add(panePagamentoPrinc);
        addFormaPagamento.setVisible(true);
        addFormaPagamento.requestFocus();
      } else {
        addFormaPagamento.requestFocus();
        addFormaPagamento.setVisible(true);
      }
    }
    if (e.getSource() == this.panePagamentoPrinc.bt_refreshComboPagamento) {

      addFormaPagamento = new EventAddFormaPagamento();
      this.encheComboBoxFormaPagamento(this.panePagamentoPrinc.cbFormaPagamento);
    }

  }
}
