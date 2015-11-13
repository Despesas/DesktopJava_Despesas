/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.form;

import com.projeto.despesa.utilitarios.DateDocument;

import com.projeto.despesa.utilitarios.JMoneyField;

/**
 *
 * @author uhitlei.barbosa
 */
public class PainelDespesas extends javax.swing.JPanel {

    /**
     * Creates new form PainelDespesas
     */
    public PainelDespesas() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel9 = new javax.swing.JLabel();
    jLabel8 = new javax.swing.JLabel();
    tfObs = new javax.swing.JTextField();
    ftfDataPag = new javax.swing.JFormattedTextField();
    jLabel11 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    jLabel12 = new javax.swing.JLabel();
    cbOrigem = new javax.swing.JComboBox();
    tfUsuario = new javax.swing.JTextField();
    jLabel6 = new javax.swing.JLabel();
    tfDescricao = new javax.swing.JTextField();
    jLabel5 = new javax.swing.JLabel();
    jLabel10 = new javax.swing.JLabel();
    tfValorParcela = new JMoneyField();
    tfParcelas = new javax.swing.JTextField();
    tfParcela = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    ftfData1 = new javax.swing.JFormattedTextField();
    jLabel4 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    btAddOrigem = new javax.swing.JButton();
    btAddAtualiza = new javax.swing.JButton();
    tfValor = new JMoneyField();
    tfValorRestante = new JMoneyField();
    jLabel13 = new javax.swing.JLabel();
    tfStatus = new javax.swing.JTextField();
    ftfData = new com.projeto.despesa.utilitarios.JCalendar();

    setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

    jLabel9.setText("Valor - Parcelas");

    jLabel8.setText("Data pagamento");

    tfObs.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    tfObs.setEnabled(false);

    ftfDataPag.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    ftfDataPag.setDocument(new DateDocument());
    ftfDataPag.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    ftfDataPag.setText("20/05/2012");
    ftfDataPag.setEnabled(false);

    jLabel11.setText("Obs:");

    jLabel7.setText("Origem");

    jLabel12.setText("Usuario");

    cbOrigem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    cbOrigem.setEnabled(false);

    tfUsuario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    tfUsuario.setEnabled(false);

    jLabel6.setText("Valor - Total");

    tfDescricao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    tfDescricao.setEnabled(false);

    jLabel5.setText("Descrição");

    jLabel10.setText("Status");

    tfValorParcela.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    tfValorParcela.setText("0,00");
    tfValorParcela.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    tfValorParcela.setEnabled(false);

    tfParcelas.setText("1");
    tfParcelas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    tfParcelas.setEnabled(false);
    tfParcelas.setName("parcela"); // NOI18N

    tfParcela.setText("1");
    tfParcela.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    tfParcela.setEnabled(false);
    tfParcela.setName("parcela"); // NOI18N

    jLabel2.setText("Data");

    ftfData1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    ftfData1.setDocument(new DateDocument());
    ftfData1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    ftfData1.setText("20/05/2012");
    ftfData1.setEnabled(false);

    jLabel4.setText("de");

    jLabel3.setText("Parcela");

    btAddOrigem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/projeto/despesa/images/16_add_plus_icon.png"))); // NOI18N
    btAddOrigem.setBorderPainted(false);
    btAddOrigem.setEnabled(false);
    btAddOrigem.setMaximumSize(new java.awt.Dimension(16, 16));
    btAddOrigem.setMinimumSize(new java.awt.Dimension(16, 16));
    btAddOrigem.setOpaque(false);
    btAddOrigem.setPreferredSize(new java.awt.Dimension(20, 20));

    btAddAtualiza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/projeto/despesa/images/16Refresh.png"))); // NOI18N
    btAddAtualiza.setBorderPainted(false);
    btAddAtualiza.setEnabled(false);
    btAddAtualiza.setMaximumSize(new java.awt.Dimension(16, 16));
    btAddAtualiza.setMinimumSize(new java.awt.Dimension(16, 16));
    btAddAtualiza.setOpaque(false);
    btAddAtualiza.setPreferredSize(new java.awt.Dimension(20, 20));

    tfValor.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    //tfValor.setDocument(new MonetarioDocument());
    tfValor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    tfValor.setText("0,00");
    tfValor.setEnabled(false);
    tfValor.setName("tfValor"); // NOI18N

    tfValorRestante.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    tfValorRestante.setEditable(false);
    tfValorRestante.setText("0,00");
    tfValorRestante.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    tfValorRestante.setEnabled(false);

    jLabel13.setText("Valor - Restante (A+R) ");

    tfStatus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    tfStatus.setEnabled(false);

    ftfData.setEnabled(false);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel6)
                  .addComponent(tfValor, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel7)
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                      .addComponent(btAddOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addComponent(btAddAtualiza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))))
              .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel2)
                  .addComponent(ftfData, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel8)
                  .addComponent(ftfDataPag, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                  .addGroup(layout.createSequentialGroup()
                    .addComponent(tfParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20))
                  .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel3)
                    .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel4)
                  .addComponent(tfParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
              .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel9)
                  .addComponent(tfValorParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel10)
                  .addComponent(tfStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel11)
                  .addComponent(tfObs, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
              .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(tfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel5)
                  .addComponent(tfDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addComponent(tfValorRestante, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ftfData1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(jLabel12)
          .addComponent(jLabel5))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(tfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(tfDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel6)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(tfValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel7)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(cbOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
              .addComponent(jLabel2)
              .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
              .addComponent(jLabel8)
              .addGap(2, 2, 2)
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(ftfDataPag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(ftfData, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(jLabel4))
              .addGap(2, 2, 2)
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(tfParcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(tfParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
          .addComponent(btAddOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(btAddAtualiza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(5, 5, 5)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel9)
            .addGap(23, 23, 23))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel10)
            .addGap(1, 1, 1)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(tfValorParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(tfStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
          .addComponent(tfObs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel11)
            .addGap(22, 22, 22)))
        .addComponent(jLabel13)
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(tfValorRestante, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGroup(layout.createSequentialGroup()
            .addComponent(ftfData1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(3, 3, 3)))
        .addGap(15, 15, 15))
    );
  }// </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  public javax.swing.JButton btAddAtualiza;
  public javax.swing.JButton btAddOrigem;
  public javax.swing.JComboBox cbOrigem;
  public com.projeto.despesa.utilitarios.JCalendar ftfData;
  public javax.swing.JFormattedTextField ftfData1;
  public javax.swing.JFormattedTextField ftfDataPag;
  public javax.swing.JLabel jLabel10;
  public javax.swing.JLabel jLabel11;
  public javax.swing.JLabel jLabel12;
  public javax.swing.JLabel jLabel13;
  public javax.swing.JLabel jLabel2;
  public javax.swing.JLabel jLabel3;
  public javax.swing.JLabel jLabel4;
  public javax.swing.JLabel jLabel5;
  public javax.swing.JLabel jLabel6;
  public javax.swing.JLabel jLabel7;
  public javax.swing.JLabel jLabel8;
  public javax.swing.JLabel jLabel9;
  public javax.swing.JTextField tfDescricao;
  public javax.swing.JTextField tfObs;
  public javax.swing.JTextField tfParcela;
  public javax.swing.JTextField tfParcelas;
  public javax.swing.JTextField tfStatus;
  public javax.swing.JTextField tfUsuario;
  public javax.swing.JFormattedTextField tfValor;
  public javax.swing.JTextField tfValorParcela;
  public javax.swing.JTextField tfValorRestante;
  // End of variables declaration//GEN-END:variables
}