/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.event;

import com.projeto.despesa.dao.OriundoDAO;
import com.projeto.despesa.dto.Oriundo;
import com.projeto.despesa.form.AddOrigemPag;
import com.projeto.despesa.starter.StartDespesas;
import com.projeto.despesa.utilitarios.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author WILL
 */
public class EventAddOrigem extends AddOrigemPag implements ActionListener {

    String descricao;
    String data;
    String status;
    String obs;
    String valor;
    Integer parcela;
    Integer parcelas;

    public EventAddOrigem(int parcela,int parcelas,String data, String obs, 
            String descricao, String status, String valor) {

        this.descricao = descricao;
        this.data= data;
        this.status=status;
        this.obs= obs;
        this.parcela=parcela;
        this.parcelas=parcelas;
        this.valor=valor;

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        this.setIconImage(new ImageIcon(this.getClass().getResource("/com/projeto/despesa/images/TributsIco32.png")).getImage());


        //   ftfDiaFechamento = new JFormattedTextField(new FormatarCampo().getIntDoisDigt());

        btGravar.addActionListener(this);
        btCancelar.addActionListener(this);
        tfData.setText(new Data().getDataAtual());
    }

    private void gravarOrigem() {
        Oriundo oriundo = new Oriundo();
        oriundo.setData_cadastro(new Data().getDataBanco(tfData.getText()));
        oriundo.setDescricao(tfDescricao.getText());
        oriundo.setDia_fechamento(Integer.parseInt(ftfDiaFechamento.getText()));
        oriundo.setDia_pag(Integer.parseInt(ftfDiaPagamento.getText()));
        try {
            new OriundoDAO().salvar(oriundo);
            
           // new StartDespesas();
        } catch (SQLException ex) {
            Logger.getLogger(EventAddOrigem.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            new StartDespesas(this.parcela, this.parcelas, this.data, this.obs, this.descricao, this.status, this.valor);            
            this.dispose();
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btGravar) {
            gravarOrigem();
        }
        if(e.getSource() ==btCancelar){
           new StartDespesas(this.parcela, this.parcelas, this.data, this.obs, this.descricao, this.status, this.valor);            
            this.dispose(); 
        }
    }
}
