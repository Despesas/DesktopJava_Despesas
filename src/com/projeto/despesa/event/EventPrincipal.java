/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.event;

import com.projeto.despesa.dao.DespesasDAO;
import com.projeto.despesa.dao.OriundoDAO;
import com.projeto.despesa.dto.Despesas;
import com.projeto.despesa.dto.Oriundo;
import com.projeto.despesa.form.Principal;
import com.projeto.despesa.starter.StartAlteraDespesas;
import com.projeto.despesa.starter.StartCadastroPagamento;
import com.projeto.despesa.starter.StartDespesas;
import com.projeto.despesa.utilitarios.Data;
import com.projeto.despesa.utilitarios.FormatarCampo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author uhitlei.barbosa
 */
public class EventPrincipal extends Principal implements ActionListener {

  StartAlteraDespesas alteraDespesa;
  StartDespesas cadastroDespesa;
  StartCadastroPagamento CadastroPagamento;
  Integer quant = 0;
  Double valor_parcela;
  Double valor;
  Float valor_restante;
  List<Despesas> listDespesas;
  List<Oriundo> listOrigem;
  String[] colunaDespesa = {"Código", "Descriçao", "Origem Pag.", "Parcela", "Data Pag.", "Data Lanç.", "Valor", "Valor Total", "Valor Restante"};
  public DefaultTableModel tmDespesa = new DefaultTableModel(null, colunaDespesa) {

    public boolean isCellEditable(int row, int column) {
      return false;
    }
  };

  public EventPrincipal() {
    zeraValores();
    listOrigem = getEncheListOrigem();
    listarOrigensCB(listOrigem);
    getInit();
  }

  private void getInit() {
//    this.setExtendedState(MAXIMIZED_BOTH);
    setLocationRelativeTo(null);

    setResizable(false);
    //  AWTUtilities.setWindowOpacity(this, 1.0F);
    this.setIconImage(new ImageIcon(this.getClass().getResource("/com/projeto/despesa/images/TributsIco32.png")).getImage());


    painelTabelaDespesas.tbTabela.setModel(tmDespesa);


    btAddDespesas.addActionListener(this);
    cbOrigenPag.addActionListener(this);
    cbMes.addActionListener(this);
    tfAno.addActionListener(this);
    mnuIDespesasEditar.addActionListener(this);
    mnuIDespesasNovo.addActionListener(this);
    btAtualizaTable.addActionListener(this);
    btPagamento.addActionListener(this);
    
    Date dt1 = new Date();
    GregorianCalendar cal = new GregorianCalendar();
    cal.setTime(dt1);
    tfAno.setText(String.valueOf(cal.get(Calendar.YEAR)));
    cbMes.setSelectedIndex(cal.get(Calendar.MONTH)+1);

    
   // Date dt1 = new Date();
   // GregorianCalendar cal = new GregorianCalendar();
    cal.setTime(dt1);
    tfAno.setText(String.valueOf(cal.get(Calendar.YEAR)));
    cbMes.setSelectedIndex(cal.get(Calendar.MONTH)+1);
    
  }

  private boolean listaVendas(List<Oriundo> listOrigem) {
    Calendar dat = Calendar.getInstance();
    int ano = 0;
    int cod_o = 0;
    int mes = 0;

    if (!tfAno.getText().equalsIgnoreCase("")) {
      ano = Integer.parseInt(tfAno.getText());
    }
    if (cbOrigenPag.getSelectedIndex() > 0) {
      cod_o = listOrigem.get(cbOrigenPag.getSelectedIndex() - 1).getCodigo();
    }
    if (cbMes.getSelectedIndex() > 0) {
      mes = cbMes.getSelectedIndex();
    }

    try {
      listDespesas = new DespesasDAO().listarByMes(mes, ano, cod_o);
      mostrarVendas(listDespesas);
      return true;


    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(this, "Nenhum Tipo de despesa: \n SQLException\n" + ex.getMessage(), "Mensagem do Sistema - ListarDespesas", 0);
      zeraTabela();
      return false;
    } catch (NullPointerException ex) {
      JOptionPane.showMessageDialog(this, "Nenhum Tipo de despesa: \n NullPointerException\n" + ex.getMessage(), "Mensagem do Sistema - ListarDespesas", 0);
      zeraTabela();
      return false;
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this, "Nenhum Tipo de despesa: \n Exception\n" + ex.getMessage(), "Mensagem do Sistema - ListarDespesas", 0);
      zeraTabela();
      return false;
    }



  }

  private void mostrarVendas(List<Despesas> despesas) {
    zeraValores();


    try {
      if (despesas.size() <= 0) {
        JOptionPane.showMessageDialog(this, "Nenhum Lançamento Cadastrada", "Mensagem do Sistema - mostrarTipoDespesa", 0);
        zeraTabela();
        zeraValores();
      } else {
        zeraTabela();
        String[] campos = new String[]{null, null, null, null, null, null, null, null, null};//"Código", "Descriçao", "Origem Pag.", "Parcela", "Data Pag.", "Data Lanç.", "Valor", "Valor Total", "Valor Restante
        int QuantLancamento = 0;
        for (int i = 0; i < despesas.size(); i++) {
          Calendar dtAtual = Calendar.getInstance();
          Calendar dtPag = new Data().getDataforCalendar(new Data().getDataTela(despesas.get(i).getData_pag()));

          Float valorp = despesas.get(i).getValor_parcela();
          Float valort = despesas.get(i).getValor_parcela() * despesas.get(i).getParcelas();
          if (dtAtual.compareTo(dtPag) >= 0) {
            this.quant = (despesas.get(i).getParcelas() - despesas.get(i).getParcela());
          } else {
            this.quant = (despesas.get(i).getParcelas() - despesas.get(i).getParcela()) + 1;
          }

          this.valor_restante = (valorp * quant);
          /*
           * quant = (parcelas - parcela)+1; Double valor_restante =
           * (valorParcela * quant);
           */

          tmDespesa.addRow(campos);
          tmDespesa.setValueAt(despesas.get(i).getCodigo(), i, 0);
          tmDespesa.setValueAt(despesas.get(i).getDescricao(), i, 1);
          tmDespesa.setValueAt(getOregiemDesc(despesas.get(i).getCod_orindo(), listOrigem), i, 2);//getOregiemDesc(despesas.get(i).getCod_orindo(), listOrigem)
          tmDespesa.setValueAt(despesas.get(i).getParcela() + " de " + despesas.get(i).getParcelas(), i, 3);
          tmDespesa.setValueAt(new Data().getDataTela(despesas.get(i).getData_pag()), i, 4);
          tmDespesa.setValueAt(new Data().getDataTela(despesas.get(i).getData_desp()), i, 5);
          tmDespesa.setValueAt(new FormatarCampo().numberFormat.format(despesas.get(i).getValor_parcela()), i, 6);
          tmDespesa.setValueAt(new FormatarCampo().numberFormat.format(despesas.get(i).getValor()), i, 7);
          tmDespesa.setValueAt(new FormatarCampo().numberFormat.format(valor_restante), i, 8);
          painelTabelaDespesas.tfRegistroPosicaoTB.setText(despesas.get(i).getSql());
          // tmDespesa.setValueAt(despesas.get(i).getValor_restante(), i, 8);

          this.valor = Double.parseDouble(new FormatarCampo().numberFormat.format(this.valor + despesas.get(i).getValor()).replace(".", "").replace(",", "."));
          this.valor_parcela = Double.parseDouble(new FormatarCampo().numberFormat.format(this.valor_parcela + despesas.get(i).getValor_parcela()).replace(".", "").replace(",", "."));

          //  String valorS = new FormatarCampo().numberFormat.format(this.valor + despesas.get(i).getValor()).replace(".", "").replace(",", ".");


        }
        lbValor.setText(new DecimalFormat().format(this.valor_parcela));
//                lbValorTotal.setText(new DecimalFormat().format(this.valor));
        
      }
    } catch (NullPointerException ex) {
      JOptionPane.showMessageDialog(this, "Nenhum Tipo de despesa: \n NullPointerException\n" + ex.getMessage(), "Mensagem do Sistema - MostrarDespesas", 0);
      zeraTabela();
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this, "Nenhum Tipo de despesa: \n Exception\n" + ex.getMessage(), "Mensagem do Sistema - MostrarDespesas", 0);
      zeraTabela();
    }
        new FormatarCampo().packColumns(painelTabelaDespesas.tbTabela, 2);
  }

  private void zeraTabela() {
    while (tmDespesa.getRowCount() > 0) {
      tmDespesa.removeRow(0);
    }
  }

  private void zeraValores() {
    this.valor = 0.0;
    this.valor_parcela = 0.0;
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

  private void listarOrigensCB(List<Oriundo> listOrigem) {
    cbOrigenPag.removeAllItems();
    cbOrigenPag.addItem("TUDO");
    for (int i = 0; i < listOrigem.size(); i++) {
      cbOrigenPag.addItem(listOrigem.get(i).getDescricao());
    }
  }

  private String getOregiemDesc(Integer cod_orindo, List<Oriundo> listOrigem) {
    String desc = "";
    for (int i = 0; i < listOrigem.size(); i++) {
      if (listOrigem.get(i).getCodigo() == cod_orindo) {
//                System.out.println("List 1 Des: " + listOrigem.get(i).getDescricao() + ": " + listOrigem.get(i).getCodigo());
        desc = "" + listOrigem.get(i).getDescricao();
      }
    }
    return desc;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btAddDespesas || e.getSource() == mnuIDespesasNovo) {
      cadastroDespesa = new StartDespesas();

      // cadastroDespesa.setVisible(true);
    }
    if (e.getSource() == mnuIDespesasEditar) {
      alteraDespesa = new StartAlteraDespesas();

      // cadastroDespesa.setVisible(true);
    }
    
    if (e.getSource() == btPagamento) {
      CadastroPagamento = new StartCadastroPagamento();

      // cadastroDespesa.setVisible(true);
    }
    if (e.getSource() == cbOrigenPag || e.getSource() == tfAno
            || e.getSource() == cbMes || e.getSource() == btAtualizaTable) {
      if (listaVendas(listOrigem)) {
      } else {
        zeraTabela();
      }
    }
  }
}
