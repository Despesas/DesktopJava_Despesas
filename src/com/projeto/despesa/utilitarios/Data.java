/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.utilitarios;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author WILL
 */
public class Data {

    DateFormat formatoTextField = new SimpleDateFormat("dd/MM/yyyy");
    DateFormat formatoDataBase = new SimpleDateFormat("yyyy-MM-dd");
    Calendar dataCalendar = Calendar.getInstance();
    Calendar dataFecha = Calendar.getInstance();
    Date dataBanco;
    String dataTela;

    public Data() {
    }

    public String getDataAtual() {
        dataCalendar.getInstance();
        //String dataAtual = dataCalendar.DAY_OF_MONTH+"/"+dataCalendar.MONTH+"/"+dataCalendar.YEAR;
        String dataAtual = formatoTextField.format(dataCalendar.getTime());
        return dataAtual;
    }

    public Date getDataBanco(String Data) {
        try {
            this.dataCalendar.setTime(formatoTextField.parse(Data));
            // System.out.println(formatoDataBase.format(dataCalendar.getTime()));
            this.dataBanco = Date.valueOf(formatoDataBase.format(dataCalendar.getTime()));
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de Data Errado: " + e.getMessage(), "Data", 0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Formato de Data Errado: " + e.getMessage(), "Data", 0);
        }


        return dataBanco;
    }

    public String getDataTela(java.util.Date Data) {
        try {
            // this.dataCalendar= formatoDataBase.format(Data);
            this.dataTela = formatoTextField.format(Data);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Formato de Data Errado: " + e.getMessage(), "Data", 0);
        }

        return dataTela;
    }

    public Calendar getDataforCalendar(String Data) {
        Calendar Cal = null;
        try {
            this.dataCalendar.setTime(formatoTextField.parse(Data));
            Cal = this.dataCalendar;
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de Data Errado: " + e.getMessage(), "Data - Calendar", 0);
        }


        return Cal;
    }

    public String getDataPag(int diaP, int diaF, String dataCadDesp) {
        String dataFechamnto = "";
        Calendar dataPag = Calendar.getInstance();

        try {
            this.dataCalendar.setTime(formatoTextField.parse(dataCadDesp));
            dataFechamnto = diaF + "/" + (1 + dataCalendar.getTime().getMonth()) + "/" + (1900 + dataCalendar.getTime().getYear());
            System.out.println(dataFechamnto);
            this.dataFecha.setTime(formatoTextField.parse(dataFechamnto));

            if (dataCalendar.before(dataFecha)) {
                if (1 + dataFecha.getTime().getMonth() == 12) {
                    // dataPag = diaP+"/01/"+dataCalendar.YEAR+1;
                    dataPag.set(1900 + dataCalendar.getTime().getYear() + 1, 0, diaP);

                } else {
                    // dataPag = diaP+"/"+dataCalendar.MONTH+1+"/"+dataCalendar.YEAR;
                    dataPag.set(1900 + dataCalendar.getTime().getYear(), dataFecha.getTime().getMonth() + 1, diaP);
                }
            } else {
                if (1 + dataFecha.getTime().getMonth() < 11) {
                    // dataPag = diaP+"/"+dataCalendar.MONTH+2+"/"+dataCalendar.YEAR;
                    dataPag.set(1900 + dataCalendar.getTime().getYear(), dataCalendar.getTime().getMonth() + 2, diaP);
                } else {
                    if (1 + dataFecha.getTime().getMonth() == 12) {
                        // dataPag = diaP+"/02/"+dataCalendar.YEAR+1;
                        dataPag.set(1900 + dataCalendar.getTime().getYear() + 1, 1, diaP);
                    } else {
                        if (1 + dataFecha.getTime().getMonth() == 11) {
                            //dataPag = diaP+"/01/"+dataCalendar.YEAR+1;
                            dataPag.set(1900 + dataCalendar.getTime().getYear() + 1, 0, diaP);
                        }
                    }
                }
            }


        } catch (ParseException parseException) {
        }
        String dataPagamento = formatoTextField.format(dataPag.getTime());
        return dataPagamento;
    }

    public String getProximoMes(Date data) {
        String dataProximoMes = "";
        String data1 = formatoTextField.format(data);
        System.out.println(data1);
        try {
            this.dataCalendar.setTime(formatoTextField.parse(data1));
            System.out.println(this.dataCalendar.getTime().getMonth());
            if (this.dataCalendar.getTime().getMonth() < 11) {
               // System.out.println("> 11");
                this.dataCalendar.set(1900 + this.dataCalendar.getTime().getYear(),this.dataCalendar.getTime().getMonth() + 1, this.dataCalendar.getTime().getDate());

            } else {
                System.out.println("<= 11");
                this.dataCalendar.set(1900 + this.dataCalendar.getTime().getYear() + 1, 0, this.dataCalendar.getTime().getDate());
            }


            dataProximoMes = formatoTextField.format(dataCalendar.getTime());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Formato de Data Errado: " + ex.getMessage(), "Data - Calendar", 0);
        }



        return dataProximoMes;
    }
}
