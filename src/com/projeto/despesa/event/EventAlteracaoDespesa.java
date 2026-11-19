/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.event;

import com.projeto.despesa.dao.DespesasDAO;
import com.projeto.despesa.dao.OriundoDAO;
import com.projeto.despesa.dto.Despesas;
import com.projeto.despesa.dto.Oriundo;
import com.projeto.despesa.form.AlteracaoDireta;
import com.projeto.despesa.starter.StartAddOrigemPag;
import com.projeto.despesa.utilitarios.CalculoParcela;
import com.projeto.despesa.utilitarios.Data;
import com.projeto.despesa.utilitarios.Mensagens;
import com.projeto.despesa.utilitarios.FormatarCampo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author rick.novaes
 */
public class EventAlteracaoDespesa extends AlteracaoDireta implements ActionListener {

    List<Oriundo> listOrigem;
    Boolean tipoSalvar = null;
    int Pergunta = 0;

    public EventAlteracaoDespesa() {
        getInit();

      //  painelDespesas.ftfData1.setText(new Data().getDataAtual());

    }

    public EventAlteracaoDespesa(int parcela, int parcelas, String Data, String obs,
            String descricao, String status, String valor) {
      try {
        getInit();
        novo();
        painelDespesas.tfDescricao.setText(descricao);
        painelDespesas.tfStatus.setText(status);
       // painelDespesas.ftfData1.setText((Data));
        painelDespesas.ftfData.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(Data));
        painelDespesas.tfObs.setText(obs);
        painelDespesas.tfParcela.setText("" + parcela);
        painelDespesas.tfParcelas.setText("" + parcelas);
        painelDespesas.tfValor.setText(valor);

        // painelDespesas.cbOrigem.setSelectedIndex(Index);
      } catch (ParseException ex) {
        Logger.getLogger(EventAlteracaoDespesa.class.getName()).log(Level.SEVERE, null, ex);
      }


    }

    private void getAddOrigem() throws NumberFormatException {
       // String data = painelDespesas.ftfData1.getText();
        String data = painelDespesas.ftfData.getText();
        String descricao = painelDespesas.tfDescricao.getText();
        String status = painelDespesas.tfStatus.getText();
        String obs = painelDespesas.tfObs.getText();
        String valor = painelDespesas.tfValor.getText();
        int parcela = Integer.parseInt(painelDespesas.tfParcela.getText());
        int parcelas = Integer.parseInt(painelDespesas.tfParcelas.getText());


        new StartAddOrigemPag(parcela, parcelas, data, obs, descricao, status, valor);

        this.dispose();
    }

    private boolean getInit() {
        listOrigem = getEncheList();
        listarOrigensCB(listOrigem);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        this.setIconImage(new ImageIcon(this.getClass().getResource("/com/projeto/despesa/images/TributsIco32.png")).getImage());
        painelButons.btProcurar.addActionListener(this);
        painelButons.btSalvar.addActionListener(this);
        painelButons.btAlterar.addActionListener(this);
        painelButons.btExcluir.addActionListener(this);
//        painelButons.btFechar.addActionListener(this);
        painelButons.btCancelar.addActionListener(this);
        painelDespesas.cbOrigem.addActionListener(this);
        painelDespesas.btAddOrigem.addActionListener(this);
        painelDespesas.btAddAtualiza.addActionListener(this);
        painelDespesas.tfValor.addActionListener(this);
        painelDespesas.tfParcela.addActionListener(this);
        painelDespesas.tfParcelas.addActionListener(this);
        tfCodigo.addActionListener(this);

        return true;
    }

    private List<Oriundo> getEncheList() {
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
        painelDespesas.cbOrigem.removeAllItems();
        painelDespesas.cbOrigem.addItem("Selecione uma Origem");
        for (int i = 0; i < listOrigem.size(); i++) {
            painelDespesas.cbOrigem.addItem(listOrigem.get(i).getDescricao());
        }
    }

    private Despesas getEncheDespesa(Despesas despesa, int parcelas, int parcela, float valor, float valorParcela, float valorRestante, Data useData) {
        despesa.setCodigo(Integer.parseInt(tfCodigo.getText()));
        despesa.setDescricao(painelDespesas.tfDescricao.getText());
        System.out.println(painelDespesas.tfDescricao.getText());
        despesa.setParcelas(parcelas);
        despesa.setParcela(parcela);
        despesa.setValor(valor);
        despesa.setValor_parcela(valorParcela);
//        despesa.setValor_restante(valorRestante);
        despesa.setStatus(painelDespesas.tfStatus.getText());
        despesa.setObs(painelDespesas.tfObs.getText());
        despesa.setCod_usuario(1);
        despesa.setCod_orindo(listOrigem.get(painelDespesas.cbOrigem.getSelectedIndex() - 1).getCodigo());
        //despesa.setData_desp(useData.getDataBanco(painelDespesas.ftfData1.getText()));
        despesa.setData_desp(useData.getDataBanco(painelDespesas.ftfData.getText()));
        despesa.setData_pag(useData.getDataBanco(painelDespesas.ftfDataPag.getText()));

        return despesa;
    }

    public void alterar() {
        Despesas desp;
        try {
            int parcelas = Integer.parseInt(painelDespesas.tfParcelas.getText().trim());
            int parcela = Integer.parseInt(painelDespesas.tfParcela.getText().trim());
            if (!CalculoParcela.parcelaValida(parcela, parcelas)) {
                avisoParcelaInvalida();
                return;
            }
            if (painelDespesas.cbOrigem.getSelectedIndex() <= 0) {
                JOptionPane.showMessageDialog(this, "Selecione a origem do pagamento.", "Mensagem do Sistema - Alterar", 0);
                return;
            }
            float valor = (float) CalculoParcela.parseValor(painelDespesas.tfValor.getText());
            float valorParcela = (float) CalculoParcela.parseValor(painelDespesas.tfValorParcela.getText());
            float valorRestante = 0f; // nao e gravado (coluna valor_restante desativada)
            desp = getEncheDespesa(new Despesas(), parcelas, parcela, valor, valorParcela, valorRestante, new Data());
        } catch (NumberFormatException e) {
            Mensagens.erro("Mensagem do Sistema - Alterar", "Informe numeros validos em codigo, parcelas e valores.", e);
            return;
        }
        try {
            new DespesasDAO().atualizar(desp);
            cancela();
        } catch (SQLException ex) {
            Mensagens.erro("Mensagem do Sistema - Alterar", "Nao foi possivel alterar a despesa.", ex);
        }
    }

    public void incluir() {
    }

    private void inserirDespesaParcelada(Despesas despesa, int parcelas, int parcela) {
    }

    private void getValorTotal() {
        try {
            int parcelas = Integer.parseInt(painelDespesas.tfParcelas.getText().trim());
            int parcela = Integer.parseInt(painelDespesas.tfParcela.getText().trim());
            if (!CalculoParcela.parcelaValida(parcela, parcelas)) {
                avisoParcelaInvalida();
                return;
            }
            double valorParcela = CalculoParcela.parseValor(painelDespesas.tfValorParcela.getText());
            double valor = CalculoParcela.valorTotal(valorParcela, parcelas);
            double valorRestante = CalculoParcela.valorRestante(valorParcela, CalculoParcela.parcelasRestantes(parcelas, parcela, false));
            painelDespesas.tfValor.setText(CalculoParcela.formatarValor(valor));
            painelDespesas.tfValorRestante.setText(CalculoParcela.formatarValor(valorRestante));
        } catch (NumberFormatException e) {
            Mensagens.erro("Valor", "Informe numeros validos em parcelas e valores.", e);
        }
    }
    
    private void getValorParcela() {
        try {
            int parcelas = Integer.parseInt(painelDespesas.tfParcelas.getText().trim());
            int parcela = Integer.parseInt(painelDespesas.tfParcela.getText().trim());
            if (!CalculoParcela.parcelaValida(parcela, parcelas)) {
                avisoParcelaInvalida();
                return;
            }
            double valor = CalculoParcela.parseValor(painelDespesas.tfValor.getText());
            double valorParcela = CalculoParcela.valorParcela(valor, parcelas);
            double valorRestante = CalculoParcela.valorRestante(valorParcela, CalculoParcela.parcelasRestantes(parcelas, parcela, false));
            painelDespesas.tfValorParcela.setText(CalculoParcela.formatarValor(valorParcela));
            painelDespesas.tfValorRestante.setText(CalculoParcela.formatarValor(valorRestante));
        } catch (NumberFormatException e) {
            Mensagens.erro("Valor", "Informe numeros validos em parcelas e valores.", e);
        }
    }

    private void avisoParcelaInvalida() {
        JOptionPane.showMessageDialog(null, "a Parcela Atual deve ser menor ou igual a quantidade de Parcelas\n e maior que 0(zero)", "Quantidade de Parcelas", 0);
        painelDespesas.tfParcela.setText("1");
    }

    public void cancela() {
//        this.dispose();
        painelButons.btSalvar.setEnabled(false);
        painelButons.btCancelar.setEnabled(false);
        painelButons.btExcluir.setEnabled(false);
        painelButons.btAlterar.setEnabled(true);
        painelButons.btExcluir.setEnabled(true);
//        painelButons.btFechar.setEnabled(true);
        painelButons.btProcurar.setEnabled(true);
        // painelTabela1.tbTabela.setEnabled(true);
//        new FormatarCampo().limpaCampo(this.painelDespesas);
        // new FormatarCampo().limpaCampo(this.pPainel2);
        new FormatarCampo().habilitarCampo(this.painelDespesas, false);
        //   new FormatarCampo().habilitarButton(this.painelDespesas, false);
        // new FormatarCampo().habilitarCampo(this.pPainel2, false);
        painelDespesas.btAddAtualiza.setEnabled(false);
        painelDespesas.btAddOrigem.setEnabled(false);
        this.tfCodigo.enable(true);


    }

    private void altera() {
        tipoSalvar = false;
        painelButons.btSalvar.setEnabled(true);
        painelButons.btCancelar.setEnabled(true);
//        painelButons.btExcluir.setEnabled(false);
        painelButons.btAlterar.setEnabled(false);
        painelButons.btExcluir.setEnabled(false);

        painelButons.btProcurar.setEnabled(false);
//        painelButons.btFechar.setEnabled(false);
        this.tfCodigo.enable(false);

        new FormatarCampo().habilitarCampo(this.painelDespesas, true);
        // new FormatarCampo().habilitarCampo(this.pPainel2, true);

    }

    private void novo() {
      try {
        tipoSalvar = true;
        painelButons.btSalvar.setEnabled(true);
        painelButons.btCancelar.setEnabled(true);
//        painelButons.btFechar.setEnabled(false);
        painelButons.btProcurar.setEnabled(false);
//        painelButons.btAlterar.setEnabled(false);
//        painelButons.btExcluir.setEnabled(false);
        //    paineltable.tbTabela.setEnabled(false);
        new FormatarCampo().habilitarCampo(this.painelDespesas, true);
        // new FormatarCampo().habilitarButton(this.painelDespesas, true);
        // new FormatarCampo().habilitarCampo(this.pPainel2, true);
        new FormatarCampo().limpaCampo(this.painelDespesas);
        //  new FormatarCampo().limpaCampo(this.pPainel2);
        //  MostrarDataTela();
        //tfValorTotal.requestFocus();
        //painelDespesas.ftfData1.setText(new Data().getDataAtual());
        painelDespesas.ftfData.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(new Data().getDataAtual()));
        painelDespesas.tfParcelas.setText("1");
        painelDespesas.tfParcela.setText("1");
        painelDespesas.btAddAtualiza.setEnabled(true);
        painelDespesas.btAddOrigem.setEnabled(true);
      } catch (ParseException ex) {
        Logger.getLogger(EventAlteracaoDespesa.class.getName()).log(Level.SEVERE, null, ex);
      }



    }

    private void verificarSalvar() {
        if (tipoSalvar) {
            incluir();
        } else {
            alterar();
        }
    }

    private void getDataPagamento(int index) {
//        Calendar cal = Calendar.getInstance();
//        cal.set(2010,1,10);
//        //cal.set
//        JOptionPane.showMessageDialog(this, 1900+cal.getTime().getYear()+": Ano, "+cal.getTime().getMonth()+": Mes,  "+ cal.getTime().getDate()+": DIA,");
        // painelDespesas.ftfDataPag.setText();
        if (painelDespesas.cbOrigem.isEnabled()) {
            int DiaP = listOrigem.get(index).getDia_pag();
            int DiaF = listOrigem.get(index).getDia_fechamento();
           // String dataAtual = painelDespesas.ftfData1.getText();
            String dataAtual = painelDespesas.ftfData.getText();
            painelDespesas.ftfDataPag.setValue(new Data().getDataPag(DiaP, DiaF, dataAtual));
        } else {
            for (int i = 0; i < listOrigem.size(); i++) {
                System.out.println(listOrigem.get(i).getDia_pag());
                System.out.println(painelDespesas.cbOrigem.getItemCount());

            }

        }
    }

    private void localizar() {
        int codigo = 0;
        if (!tfCodigo.getText().equalsIgnoreCase("")) {
            codigo = Integer.parseInt(tfCodigo.getText());
            try {
                Despesas desp = new DespesasDAO().localizar(codigo);
                if (desp != null) {
                    encheCampos(desp);
                } else {
                    JOptionPane.showMessageDialog(null, "Despesa não encontrada", "Localizar", 0);
                    painelButons.btAlterar.setEnabled(false);
                    painelButons.btExcluir.setEnabled(false);

                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQLException Erro: \n" + ex.getMessage(), "Classe: Event Func. Localizar()", 0);
                painelButons.btAlterar.setEnabled(false);
                painelButons.btExcluir.setEnabled(false);
                new FormatarCampo().limpaCampo(painelDespesas);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "NullPointerException Erro: \n" + ex.getMessage() + "\n Despesa não encontrada", "Classe: Event Func. Localizar()", 0);
                painelButons.btAlterar.setEnabled(false);
                painelButons.btExcluir.setEnabled(false);
                new FormatarCampo().limpaCampo(painelDespesas);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Exception Erro: \n" + ex.getMessage(), "Classe: Event Func. Localizar()", 0);
                painelButons.btAlterar.setEnabled(false);
                painelButons.btExcluir.setEnabled(false);
                new FormatarCampo().limpaCampo(painelDespesas);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Exception Erro: \n" + "Digite um Codigo", "Classe: Event Func. Localizar()", 0);
        }
    }

    private void encheCampos(Despesas desp) {
      try {
        String dataLanc = new Data().getDataTela(desp.getData_desp());
        java.util.Date dt_lanc = new SimpleDateFormat("dd/MM/yyyy").parse(dataLanc);
        painelDespesas.tfDescricao.setText(desp.getDescricao());
        painelDespesas.tfObs.setText(desp.getObs());
        painelDespesas.tfParcela.setText(desp.getParcela().toString());
        painelDespesas.tfParcelas.setText(desp.getParcelas().toString());
        // antes: Float.toString ("1234.5") era relido como pt-BR e virava 12345
        painelDespesas.tfValor.setText(CalculoParcela.formatarValor(desp.getValor()));
        painelDespesas.tfValorParcela.setText(CalculoParcela.formatarValor(desp.getValor_parcela()));
//        painelDespesas.tfValorRestante.setText(desp.getValor_restante().toString());
       // painelDespesas.ftfData1.setValue(new Data().getDataTela(desp.getData_desp()).toString());
        painelDespesas.ftfData.setDate(dt_lanc);
        // System.out.println(desp.getData_desp().toString());
        painelDespesas.ftfDataPag.setValue(new Data().getDataTela(desp.getData_pag()).toString());
        painelDespesas.tfStatus.setText(desp.getStatus().toString());
        painelDespesas.tfUsuario.setText(desp.getCod_usuario().toString());
        painelDespesas.cbOrigem.setSelectedIndex(getIndex(desp.getCod_orindo()));

        painelButons.btAlterar.setEnabled(true);
        painelButons.btExcluir.setEnabled(true);
      } catch (ParseException ex) {
        Logger.getLogger(EventAlteracaoDespesa.class.getName()).log(Level.SEVERE, null, ex);
      }


    }

    private int getIndex(Integer cod_orindo) {
        for (int i = 0; i < listOrigem.size(); i++) {
            if (listOrigem.get(i).getCodigo().equals(cod_orindo)) {
                return i + 1;
            }
        }

        return 0;
    }

    private void excluir() {
        Pergunta = JOptionPane.showConfirmDialog(this, "Deseja realmente Excluir os Dados?", "Mensagem do Sistema", JOptionPane.YES_NO_OPTION);
        if (Pergunta == JOptionPane.YES_OPTION) {

            try {
                Despesas desp = new Despesas();
                desp.setCodigo(Integer.parseInt(tfCodigo.getText()));
                new DespesasDAO().excluir(desp);
                new FormatarCampo().habilitarCampo(painelDespesas, false);
                cancela();

            } catch (SQLException ex) {
                Mensagens.erro("Mensagem do Sistema - Excluir", "Nao foi possivel excluir a despesa.", ex);
            } catch (NumberFormatException ex) {
                Mensagens.erro("Mensagem do Sistema - Excluir", "Codigo invalido.", ex);
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == painelButons.btProcurar || e.getSource() == tfCodigo) {
            localizar();
        }

        if (e.getSource() == painelButons.btSalvar) {
            verificarSalvar();
        }

        if (e.getSource() == painelButons.btExcluir) {
            excluir();
        }
        if (e.getSource() == painelDespesas.cbOrigem) {
            if (painelDespesas.cbOrigem.isEnabled()) {
                if (listOrigem.isEmpty()) {
                } else if (painelDespesas.cbOrigem.getSelectedIndex() == 0) {
                    painelDespesas.ftfDataPag.setText("");
                } else {
                    getDataPagamento(painelDespesas.cbOrigem.getSelectedIndex() - 1);
                }
            }
        }

        if (e.getSource() == painelDespesas.tfValor
                || e.getSource() == painelDespesas.tfParcela
                || e.getSource() == painelDespesas.tfParcelas) {
            getValorParcela();

        }
        if (e.getSource() == painelDespesas.tfValorParcela) {
            getValorTotal();

        }

//        if (e.getSource() == painelButons.btFechar) {
//            cancela();
//            this.dispose();
//            //this.setVisible(false);
//        }

        if (e.getSource() == painelDespesas.btAddOrigem) {

            getAddOrigem();

        }

        if (e.getSource() == painelDespesas.btAddAtualiza) {
            //
        }


        if (e.getSource() == painelButons.btAlterar) {
            altera();
        }

        if (e.getSource() == painelButons.btCancelar) {
            cancela();
        }
    }
}
