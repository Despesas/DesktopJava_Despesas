/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PainelTabela.java
 *
 * Created on 30/11/2011, 20:14:26
 */
package com.projeto.despesa.form;

/**
 *
 * @author WILL
 */
public class PainelTabela extends javax.swing.JPanel {

    /** Creates new form PainelTabela */
    public PainelTabela() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbTabela = new javax.swing.JTable();
        brRecuarTudo = new javax.swing.JButton();
        btRecuarUm = new javax.swing.JButton();
        btAvancarUm = new javax.swing.JButton();
        btAvancarTudo = new javax.swing.JButton();
        tfRegistroPosicaoTB = new javax.swing.JTextField();

        tbTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbTabela);

        brRecuarTudo.setText("<<");

        btRecuarUm.setText("<");

        btAvancarUm.setText(">");

        btAvancarTudo.setText(">>");

        tfRegistroPosicaoTB.setForeground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(brRecuarTudo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btRecuarUm, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfRegistroPosicaoTB, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btAvancarUm, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btAvancarTudo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {brRecuarTudo, btAvancarTudo, btAvancarUm, btRecuarUm});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(brRecuarTudo)
                    .addComponent(btRecuarUm)
                    .addComponent(btAvancarUm)
                    .addComponent(btAvancarTudo)
                    .addComponent(tfRegistroPosicaoTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton brRecuarTudo;
    public javax.swing.JButton btAvancarTudo;
    public javax.swing.JButton btAvancarUm;
    public javax.swing.JButton btRecuarUm;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tbTabela;
    public javax.swing.JTextField tfRegistroPosicaoTB;
    // End of variables declaration//GEN-END:variables
}
