package com.projeto.despesa.utilitarios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Calendar;
import org.junit.Test;

public class DataTest {

    private final Data data = new Data();

    @Test
    public void getDataBancoConverteFormatoDaTela() {
        java.sql.Date d = data.getDataBanco("15/03/2015");
        assertEquals("2015-03-15", d.toString());
    }

    @Test
    public void getDataBancoRejeitaDataInexistente() {
        // antes 31/02/2015 virava 03/03/2015 silenciosamente
        assertNull(data.getDataBanco("31/02/2015"));
        assertNull(data.getDataBanco("aa/bb/cccc"));
        assertNull(data.getDataBanco(null));
    }

    @Test
    public void getDataTela() {
        assertEquals("01/12/2016", data.getDataTela(java.sql.Date.valueOf("2016-12-01")));
        assertEquals("", data.getDataTela(null));
    }

    @Test
    public void getDataforCalendar() {
        Calendar c = data.getDataforCalendar("29/02/2016");
        assertNotNull(c);
        assertEquals(2016, c.get(Calendar.YEAR));
        assertEquals(Calendar.FEBRUARY, c.get(Calendar.MONTH));
        assertEquals(29, c.get(Calendar.DAY_OF_MONTH));
        assertNull(data.getDataforCalendar("29/02/2015"));
    }

    @Test
    public void compraAntesDoFechamentoVenceNoMesSeguinte() {
        // cartao fecha dia 20 e vence dia 5
        assertEquals("05/04/2015", data.getDataPag(5, 20, "10/03/2015"));
    }

    @Test
    public void compraNoDiaOuDepoisDoFechamentoVenceDoisMesesDepois() {
        assertEquals("05/05/2015", data.getDataPag(5, 20, "20/03/2015"));
        assertEquals("05/05/2015", data.getDataPag(5, 20, "25/03/2015"));
    }

    @Test
    public void viradaDeAno() {
        assertEquals("05/01/2016", data.getDataPag(5, 20, "10/12/2015"));
        assertEquals("05/02/2016", data.getDataPag(5, 20, "25/12/2015"));
        assertEquals("05/01/2016", data.getDataPag(5, 20, "25/11/2015"));
    }

    @Test
    public void diaDeVencimentoInexistenteVaiParaUltimoDiaDoMes() {
        assertEquals("28/02/2015", data.getDataPag(31, 25, "10/01/2015"));
        assertEquals("29/02/2016", data.getDataPag(30, 25, "10/01/2016"));
    }

    @Test
    public void dataDeCompraInvalidaDevolveVazio() {
        // antes o erro era engolido e voltava a data de hoje
        assertEquals("", data.getDataPag(5, 20, "xx"));
    }

    @Test
    public void getProximoMesMantemODia() {
        assertEquals("15/04/2015", data.getProximoMes(java.sql.Date.valueOf("2015-03-15")));
        assertEquals("15/01/2016", data.getProximoMes(java.sql.Date.valueOf("2015-12-15")));
    }

    @Test
    public void getProximoMesAjustaFimDeMes() {
        // antes 31/01 virava 03/03
        assertEquals("28/02/2015", data.getProximoMes(java.sql.Date.valueOf("2015-01-31")));
        assertEquals("29/02/2016", data.getProximoMes(java.sql.Date.valueOf("2016-01-31")));
        assertEquals("30/04/2015", data.getProximoMes(java.sql.Date.valueOf("2015-03-31")));
    }

    @Test
    public void getDataAtualNoFormatoDaTela() {
        String hoje = data.getDataAtual();
        assertEquals(10, hoje.length());
        assertNotNull(data.getDataBanco(hoje));
    }
}
