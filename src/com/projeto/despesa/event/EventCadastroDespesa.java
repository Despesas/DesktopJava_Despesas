/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.event;

import com.projeto.despesa.dao.DespesasDAO;
import com.projeto.despesa.dao.OriundoDAO;
import com.projeto.despesa.dto.Despesas;
import com.projeto.despesa.dto.Oriundo;
import com.projeto.despesa.form.CadastroDireto;
import com.projeto.despesa.starter.StartAddOrigemPag;
import com.projeto.despesa.utilitarios.Data;
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
public class EventCadastroDespesa extends CadastroDireto implements ActionListener {

    List<Oriundo> listOrigem;
   public Boolean tipoSalvar = null;

    public EventCadastroDespesa() {

        getInit();



      //  painelDespesas.ftfData.setSelectedItem(new Data().getDataAtual());





    }

    public EventCadastroDespesa(int parcela, int parcelas, String Data, String obs,
            String descricao, String status, String valor) {
      try {
        getInit();
        novo();
        painelDespesas.tfDescricao.setText(descricao);
        painelDespesas.tfStatus.setText(status);
        //painelDespesas.ftfData1.setText(Data);
        painelDespesas.ftfData.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(Data));
        painelDespesas.tfObs.setText(obs);
        painelDespesas.tfParcela.setText(""+parcela);
        painelDespesas.tfParcelas.setText("" + parcelas);
        painelDespesas.tfValor.setText(valor);

        // painelDespesas.cbOrigem.setSelectedIndex(Index);
      } catch (ParseException ex) {
        Logger.getLogger(EventCadastroDespesa.class.getName()).log(Level.SEVERE, null, ex);
      }


    }

    private void getAddOrigem() throws NumberFormatException {
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

    private Despesas getEncheDespesa(Despesas despesa, int parcelas, int parcela, float valor, float valorParcela, Data useData) {
        despesa.setDescricao(painelDespesas.tfDescricao.getText());
        despesa.setParcelas(parcelas);
        despesa.setParcela(parcela);
        despesa.setValor(valor);
        despesa.setValor_parcela(valorParcela);
        despesa.setStatus(painelDespesas.tfStatus.getText());
        despesa.setObs(painelDespesas.tfObs.getText());
        despesa.setCod_usuario(1);
        despesa.setCod_orindo(listOrigem.get(painelDespesas.cbOrigem.getSelectedIndex()-1).getCodigo());
        despesa.setData_desp(useData.getDataBanco(painelDespesas.ftfData.getText()));
       // despesa.setData_desp(useData.getDataBanco(painelDespesas.ftfData1.getText()));
        despesa.setData_pag(useData.getDataBanco(painelDespesas.ftfDataPag.getText()));
        
        return despesa;
    }

    private boolean getInit() {
        listOrigem = getEncheList();
        listarOrigensCB(listOrigem);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        this.setIconImage(new ImageIcon(this.getClass().getResource("/com/projeto/despesa/images/TributsIco32.png")).getImage());
        painelButons.btNovo.addActionListener(this);
        painelButons.btSalvar.addActionListener(this);
        painelButons.btAlterar.addActionListener(this);
        painelButons.btExcluir.addActionListener(this);
        painelButons.btFechar.addActionListener(this);
        painelButons.btCancelar.addActionListener(this);
        painelDespesas.cbOrigem.addActionListener(this);
        painelDespesas.btAddOrigem.addActionListener(this);
        painelDespesas.btAddAtualiza.addActionListener(this);
        painelDespesas.tfValor.addActionListener(this);
        painelDespesas.tfParcela.addActionListener(this);
        painelDespesas.tfParcelas.addActionListener(this);
        painelDespesas.tfValorParcela.addActionListener(this);

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
         painelDespesas.cbOrigem.addItem("Selecione ...");        
        for (int i = 0; i < listOrigem.size(); i++) {
            painelDespesas.cbOrigem.addItem(listOrigem.get(i).getDescricao());
        }
    }

    public void incluir() {

        
        Despesas despesa = null;
        int parcelas = 0;
        int parcela = 0;
        float valor = 0;
        float valorParcela = 0;
        Data useData = null;
        
        try {
            parcelas = Integer.parseInt(painelDespesas.tfParcelas.getText());
            parcela = Integer.parseInt(painelDespesas.tfParcela.getText());
            String valorS = painelDespesas.tfValor.getText();
            valorS = valorS.replace(".", "");
            valorS = valorS.replace(",", ".");
            valor = Float.parseFloat(valorS);
            valorParcela = Float.parseFloat(new FormatarCampo().numberFormat.format(valor / parcelas).replace(".", "").replace(",", "."));
            useData = new Data();
            despesa = new Despesas();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "NumberFormaterExeption Erro: " + e.getMessage(), "ClasseDAO Func.Salvar", 0);
            e.printStackTrace();
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Exeption Erro: " + ex.getMessage(), "ClasseDAO Func.Salvar", 0);
        }
        despesa = getEncheDespesa(despesa, parcelas, parcela, valor, valorParcela, useData);

        try {
            if ((parcelas - parcela) + 1 == 1) {
                System.out.println("==1");
                new DespesasDAO().salvar(despesa);

            } else {

                new DespesasDAO().salvarParcelado(despesa);
                // inserirDespesaParcelada(despesa, parcelas, parcela);
                System.out.println("CHAMOU A FUNÇÂO DESPESA PARCELADA");

            }

        } catch (Exception e) {
            
        } finally {
            cancela();
        }
    }
/*
    private void inserirDespesaParcelada(Despesas despesa, int parcelas, int parcela) {

        try {
            while ((parcelas - parcela) + 1 >= 1) {
                System.out.println("ENTROU NO WHILE");
                new DespesasDAO().salvar(despesa);
                parcela++;
                despesa.setParcela(parcela);
                String dataProxima = new Data().getProximoMes(despesa.getData_pag());
                despesa.setData_pag(new Data().getDataBanco(dataProxima));
            }
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(this, "ERRO AO INSERIR Função InserirDespesasPArcelada", "DespeasParcelada", ERROR, null);
        }
        



    }
    * 
    */

    private void getValorTotal() {
        int parcelas = Integer.parseInt(painelDespesas.tfParcelas.getText());
        int parcela = Integer.parseInt(painelDespesas.tfParcela.getText());
        
       if(parcelas >= parcela){
        String valorP = painelDespesas.tfValorParcela.getText();
        valorP = valorP.replace(".", "");
        valorP = valorP.replace(",", ".");
        Double valorParcela = Double.parseDouble(valorP) ;
        Double valor = (valorParcela * parcelas);
        
        int quant = (parcelas - parcela)+1;
        Double valor_restante = (valorParcela * quant);

        if (parcelas >= parcela) {

            valorP = new FormatarCampo().numberFormat.format(valorParcela);
            //System.out.println(v);
            painelDespesas.tfValor.setText(new FormatarCampo().numberFormat.format(valor));
            painelDespesas.tfValorRestante.setText(new FormatarCampo().numberFormat.format(valor_restante));
        }
       }else{
           JOptionPane.showMessageDialog(null, "a Parcela Atual deve ser menor ou igual a quantidade de Parcelas\n e maior que 0(zero)", "Quantidade de Parcelas", 0);
           painelDespesas.tfParcela.setText("1");
       }
    }
    
    private void getValorParcela() {
        int parcelas = Integer.parseInt(painelDespesas.tfParcelas.getText());
        int parcela = Integer.parseInt(painelDespesas.tfParcela.getText());
        
       if(parcelas >= parcela){
        String valorT = painelDespesas.tfValor.getText().replace(".", "").replace(",", ".");
        
        Double valor = Double.parseDouble(valorT);
        Double valorParcela = (valor / parcelas) ;
        
        int quant = (parcelas - parcela)+1;
        Double valor_restante = (valorParcela * quant);

        if (parcelas >= parcela) {

            valorT = new FormatarCampo().numberFormat.format(valorParcela);
            //System.out.println(v);
            painelDespesas.tfValorParcela.setText(new FormatarCampo().numberFormat.format(valorParcela));
            painelDespesas.tfValorRestante.setText(new FormatarCampo().numberFormat.format(valor_restante));
        }
       }else{
           JOptionPane.showMessageDialog(null, "a Parcela Atual deve ser menor ou igual a quantidade de Parcelas\n e maior que 0(zero)", "Quantidade de Parcelas", 0);
           painelDespesas.tfParcela.setText("1");
       }
    }

    public void alterar() {
    }

    public void cancela() {
        painelButons.btSalvar.setEnabled(false);
        painelButons.btCancelar.setEnabled(false);
        painelButons.btExcluir.setEnabled(false);
        painelButons.btAlterar.setEnabled(false);
        painelButons.btFechar.setEnabled(true);
        painelButons.btNovo.setEnabled(true);
        // painelTabela1.tbTabela.setEnabled(true);
        new FormatarCampo().limpaCampo(this.painelDespesas);
        // new FormatarCampo().limpaCampo(this.pPainel2);
        new FormatarCampo().habilitarCampo(this.painelDespesas, false);
        //   new FormatarCampo().habilitarButton(this.painelDespesas, false);
        // new FormatarCampo().habilitarCampo(this.pPainel2, false);
        painelDespesas.btAddAtualiza.setEnabled(false);
        painelDespesas.btAddOrigem.setEnabled(false);
    }

    private void altera() {
        tipoSalvar = false;
        painelButons.btSalvar.setEnabled(true);
        painelButons.btCancelar.setEnabled(true);
        painelButons.btExcluir.setEnabled(false);
        painelButons.btAlterar.setEnabled(false);
        painelButons.btNovo.setEnabled(false);
        painelButons.btFechar.setEnabled(false);

        new FormatarCampo().habilitarCampo(this.painelDespesas, true);
        // new FormatarCampo().habilitarCampo(this.pPainel2, true);

    }

    private void novo() {
        tipoSalvar = true;
        painelButons.btSalvar.setEnabled(true);
        painelButons.btCancelar.setEnabled(true);
        painelButons.btFechar.setEnabled(false);
        painelButons.btNovo.setEnabled(false);
        painelButons.btAlterar.setEnabled(false);
        painelButons.btExcluir.setEnabled(false);
        //    paineltable.tbTabela.setEnabled(false);
        new FormatarCampo().habilitarCampo(this.painelDespesas, true);
        // new FormatarCampo().habilitarButton(this.painelDespesas, true);
        // new FormatarCampo().habilitarCampo(this.pPainel2, true);
        new FormatarCampo().limpaCampo(this.painelDespesas);
        //  new FormatarCampo().limpaCampo(this.pPainel2);
        //  MostrarDataTela();
        //tfValorTotal.requestFocus();
       // painelDespesas.ftfData1.setText(new Data().getDataAtual());
       // painelDespesas.ftfData.setSelectedItem(new Data().getDataAtual());
        painelDespesas.tfParcelas.setText("1");
        painelDespesas.tfParcela.setText("1");
        painelDespesas.btAddAtualiza.setEnabled(true);
        painelDespesas.btAddOrigem.setEnabled(true);



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
            System.out.println("Lista Empty");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == painelButons.btNovo) {
            novo();
        }

        if (e.getSource() == painelButons.btSalvar) {
            verificarSalvar();
        }

        if (e.getSource() == painelButons.btExcluir) {
            //  excluir();
        }
        if (e.getSource() == painelDespesas.cbOrigem ||e.getSource() == painelDespesas.ftfData ) {
            if (listOrigem.isEmpty()) {
            } else if(painelDespesas.cbOrigem.getSelectedIndex()==0){
                painelDespesas.ftfDataPag.setText("");
            }else{
               getDataPagamento(painelDespesas.cbOrigem.getSelectedIndex()-1);
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

        if (e.getSource() == painelButons.btFechar) {
            cancela();
            this.dispose();
            //this.setVisible(false);
        }

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
