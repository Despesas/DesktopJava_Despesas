package com.projeto.despesa.utilitarios;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Conversoes de data entre a tela (dd/MM/yyyy) e o banco (java.sql.Date) e
 * calculo das datas de pagamento.
 *
 * @author WILL
 */
public class Data {

    public static final String FORMATO_TELA = "dd/MM/yyyy";

    private final DateFormat formatoTextField;

    public Data() {
        formatoTextField = new SimpleDateFormat(FORMATO_TELA);
        // datas como 31/02/2015 eram aceitas e viravam 03/03/2015
        formatoTextField.setLenient(false);
    }

    public String getDataAtual() {
        return formatoTextField.format(Calendar.getInstance().getTime());
    }

    /** Converte "dd/MM/yyyy" para java.sql.Date; devolve null se a data for invalida. */
    public Date getDataBanco(String data) {
        try {
            return new Date(parse(data).getTime());
        } catch (ParseException e) {
            Mensagens.erro("Data", "Formato de Data Errado: " + data, e);
            return null;
        }
    }

    public String getDataTela(java.util.Date data) {
        if (data == null) {
            return "";
        }
        return formatoTextField.format(data);
    }

    public Calendar getDataforCalendar(String data) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(parse(data));
            return cal;
        } catch (ParseException e) {
            Mensagens.erro("Data - Calendar", "Formato de Data Errado: " + data, e);
            return null;
        }
    }

    /**
     * Calcula a data de pagamento de uma compra feita em {@code dataCadDesp}
     * numa origem (cartao) que fecha no dia {@code diaF} e vence no dia
     * {@code diaP}: compras antes do fechamento vencem no mes seguinte; as
     * demais, dois meses depois. Dias inexistentes (ex.: 31 em fevereiro) sao
     * ajustados para o ultimo dia do mes.
     *
     * @return a data no formato dd/MM/yyyy, ou "" se a data da compra for invalida
     */
    public String getDataPag(int diaP, int diaF, String dataCadDesp) {
        Calendar compra = Calendar.getInstance();
        try {
            compra.setTime(parse(dataCadDesp));
        } catch (ParseException e) {
            Mensagens.erro("Data de Pagamento", "Formato de Data Errado: " + dataCadDesp, e);
            return "";
        }
        Calendar fechamento = (Calendar) compra.clone();
        definirDia(fechamento, diaF);

        Calendar pagamento = (Calendar) compra.clone();
        pagamento.set(Calendar.DAY_OF_MONTH, 1);
        pagamento.add(Calendar.MONTH, compra.before(fechamento) ? 1 : 2);
        definirDia(pagamento, diaP);
        return formatoTextField.format(pagamento.getTime());
    }

    /** Mesma data no mes seguinte (31/01 vira 28/02 ou 29/02, e nao 03/03). */
    public String getProximoMes(java.util.Date data) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.MONTH, 1);
        return formatoTextField.format(cal.getTime());
    }

    private java.util.Date parse(String data) throws ParseException {
        if (data == null) {
            throw new ParseException("data nula", 0);
        }
        return formatoTextField.parse(data.trim());
    }

    private static void definirDia(Calendar cal, int dia) {
        int ultimo = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, Math.max(1, Math.min(dia, ultimo)));
    }
}
