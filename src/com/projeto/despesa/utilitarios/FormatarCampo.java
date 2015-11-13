/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.utilitarios;

import java.awt.Component;
import java.awt.Container;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Uhitlei
 */
public class FormatarCampo {

    private MaskFormatter valor;
    private MaskFormatter dataToBanco;
    private MaskFormatter dataToTela;
    private MaskFormatter IntDoisDigt;
    private MaskFormatter IntTresDigt;
    private MaskFormatter IntAno;
    int rows = 3;
    int cols = 3;
    int margin = 2;
    public NumberFormat numberFormat = NumberFormat.getInstance();

    public FormatarCampo() {
        formatarCampos();
    }

    public void formatarCampos() {
        try {
            valor = new MaskFormatter("##,##");
            IntDoisDigt = new MaskFormatter("##");
            IntTresDigt = new MaskFormatter("###");
            IntAno = new MaskFormatter("####");
            
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    public void limpaCampo(Container cont) {
        for (Object campo : cont.getComponents()) {

            if (campo instanceof JTextField) {
                if (((JTextField) campo).getName() != "tfValor") {
                    ((JTextField) campo).setText(null);
                }
                else{
                    ((JTextField) campo).setText("0,00");
                }
            }
        }
    }

    public void habilitarCampo(Container cont, boolean ToF) {
        for (Object campo : cont.getComponents()) {
            if (campo instanceof JFormattedTextField) {
                ((JFormattedTextField) campo).setEnabled(ToF);
            }
            if (campo instanceof JComboBox) {
                if (((JComboBox) campo).getName() != "cbTipoBanco") {
                    ((JComboBox) campo).setEnabled(ToF);
                }
            }
            if (campo instanceof JTextField) {
                if (((JTextField) campo).getName() != "tfCodigo") {
                    ((JTextField) campo).setEnabled(ToF);
                }

            }

//            if (campo instanceof JButton) {
//                if (((JButton) campo).getText() != "Novo" || ((JButton) campo).getText() != "Fechar") {
//                    ((JTextField) campo).setEnabled(ToF);
//                }
//
//
//
//            }
        }
    }

//    public void habilitarButton(Container cont, boolean ToF) {
//        for (Object campo : cont.getComponents()) {
////            if (campo instanceof JFormattedTextField) {
////                ((JFormattedTextField) campo).setEnabled(ToF);
////            }
////            if (campo instanceof JComboBox) {
////                ((JComboBox) campo).setEnabled(ToF);
////            }
//            if (campo instanceof JTextField) {
//                if (((JButton) campo).getText() != "Novo" || ((JButton) campo).getText() != "Fechar") {
//                    ((JTextField) campo).setEnabled(ToF);
//                }
//
//            }
//
//
//        }
//    }
//    public void desabilitarCampo(Container cont) {
//        for (Object campo : cont.getComponents()) {
//            if (campo instanceof JFormattedTextField) {
//                ((JFormattedTextField) campo).setEnabled(false);
//            }
//            if (campo instanceof JComboBox) {
//                ((JComboBox) campo).setEnabled(false);
//            }
//            if (campo instanceof JTextField) {
//                if (((JTextField) campo).getName() != "tfCodigo") {
//                    ((JTextField) campo).setEnabled(false);
//                }
//
//            }
//
//        }
//    }
    public int getNumBoolean(String cb) {
        int Selec;
        if (cb == "SIM") {
            Selec = 1;
        } else {
            Selec = 0;
        }

        return Selec;
    }

//    JTable table = new JTable(rows, cols);        // Desabilita a possibilidade de resize
//    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);        // Adiciona dados aqui        int vColIndex = 1;
//    
//    packColumn(table, vColIndex, margin);
//------------------------FORMATAR TABELA PARA O TAMANHO DOS DADOS OU DA COLUNA HEADER----------    
    public void packColumns(JTable table, int margin) {
        for (int c = 0; c < table.getColumnCount(); c++) {
            packColumn(table, c, margin);
        }
    }    // Ajusta a largura preferida da coluna visível especificada pelo vColIndex.
// A coluna será larga o bastante para mostrar o cabeçalho da coluna e a
// célula de maior conteúdo.

    public void packColumn(JTable table, int vColIndex, int margin) {
        TableModel model = table.getModel();
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
        TableColumn col = colModel.getColumn(vColIndex);
        int width = 0;            // Obtém a largura do cabeçalho da coluna
        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }
        Component comp = renderer.getTableCellRendererComponent(table, col.getHeaderValue(), false, false, 0, 0);
        width = comp.getPreferredSize().width;            // Obtém a largura maxima da coluna de dados
        for (int r = 0; r < table.getRowCount(); r++) {
            renderer = table.getCellRenderer(r, vColIndex);
            comp = renderer.getTableCellRendererComponent(
                    table, table.getValueAt(r, vColIndex), false, false, r,
                    vColIndex);
            width = Math.max(width, comp.getPreferredSize().width);
        }
        width += 2 * margin;            // Configura a largura
        col.setPreferredWidth(width);
    }
//===================================================================================================

    /**
     * @return the valor
     */
    public MaskFormatter getValor() {
        try {
            valor = new MaskFormatter("##,##");
        } catch (ParseException ex) {
            Logger.getLogger(FormatarCampo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return valor;
    }

    /**
     * @return the dataToBanco
     */
    public MaskFormatter getDataToBanco() {
        return dataToBanco;
    }

    /**
     * @return the dataToTela
     */
    public MaskFormatter getDataToTela() {
        return dataToTela;
    }

    /**
     * @return the IntDoisDigt
     */
    public MaskFormatter getIntDoisDigt() {
        try {
            IntDoisDigt = new MaskFormatter("##");
        } catch (ParseException ex) {
            Logger.getLogger(FormatarCampo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return IntDoisDigt;
    }

    /**
     * @return the IntTresDigt
     */
    public MaskFormatter getIntTresDigt() {
        try {
            IntTresDigt = new MaskFormatter("###");
        } catch (ParseException ex) {
            Logger.getLogger(FormatarCampo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IntTresDigt;
    }

    /**
     * @return the IntAno
     */
    public MaskFormatter getIntAno() {
        try {
            IntTresDigt = new MaskFormatter("####");
        } catch (ParseException ex) {
            Logger.getLogger(FormatarCampo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IntAno;
    }
}
