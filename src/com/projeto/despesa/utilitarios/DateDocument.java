/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.utilitarios;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author WILL
 */
import java.util.Date;  
import java.util.logging.Level;  
import java.util.logging.Logger;  
import javax.swing.text.AttributeSet;  
import javax.swing.text.BadLocationException;  
import javax.swing.text.PlainDocument;  
  
public class DateDocument extends PlainDocument {  
  
    private static int TAMANHO_TEXTO = 10; //para o formato dd/MM/yyyy  
    private static String CARACTERES_DATA = "0123456789/";  
    private static String FORMATO = "dd/MM/yyyy";  
  
    public DateDocument() {  
    }  
  
    public void setDate(Date date) {  
        String strr = date == null ? "" : new java.text.SimpleDateFormat(FORMATO).format(date);  
        try {  
            remove(0, getLength());  
            super.insertString(0, strr, null);  
        } catch (BadLocationException ex) {  
            Logger.getLogger(DateDocument.class.getName()).log(Level.SEVERE, null, ex);  
        }  
    }  
  
    @Override  
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {  
        //Nao mostrar se a string for null.  
        if (str == null) {  
            return;  
        }  
  
        //Verificar o tamanho maximo permitido  
        if (getLength() > TAMANHO_TEXTO) {  
            return;  
        }  
  
        //Somente para validar os caracteres digitados  
        for (int i = 0; i < str.length(); i++) {  
            if (CARACTERES_DATA.indexOf(String.valueOf(str.charAt(i))) == -1) {  
                java.awt.Toolkit.getDefaultToolkit().beep();  
                return;  
            }  
        }  
  
        //Checar a posicao do caracter que separa a data  
        if ((offset == 2) || (offset == 5)) {  
            if (!str.equals("/")) {  
                return;  
            }  
        }  
  
        super.insertString(offset, str, attr);  
        // inserir o caracter que separa  
        if ((offset == 1) || (offset == 4)) {  
            super.insertString(offset + 1, "/", attr);  
        }  
  
        //If the user has finished entering validate the date entered by him.  
        if (offset == TAMANHO_TEXTO - 1) {  
            String strr = getText(0, getLength());  
            remove(0, getLength());  
            super.insertString(0, getCorrectDate(strr), attr);  
        }  
    }  
  
    private String getCorrectDate(String oldDate) {  
        String newDate = "";  
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(FORMATO);  
        try {  
            java.util.Date date1 = formatter.parse(oldDate);  
            newDate = formatter.format(date1);  
        } catch (java.text.ParseException ex) {  
            Logger.getLogger(DateDocument.class.getName()).log(Level.SEVERE, null, ex);  
            newDate = "";  
        }  
        return newDate;  
    }  
  
    public Date getDate() {  
        Date newDate = null;  
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(FORMATO);  
        try {  
            newDate = formatter.parse(this.getText(0, TAMANHO_TEXTO));  
        } catch (java.text.ParseException ex) {  
            //Logger.getLogger(DateDocument.class.getName()).log(Level.SEVERE, null, ex);  
        } catch (BadLocationException ex) {  
            //Logger.getLogger(DateDocument.class.getName()).log(Level.SEVERE, null, ex);  
        }  
        return newDate;  
    }  
}  