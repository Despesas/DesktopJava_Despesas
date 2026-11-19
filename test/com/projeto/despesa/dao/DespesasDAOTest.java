package com.projeto.despesa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.projeto.despesa.dto.Despesas;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class DespesasDAOTest {

    @Test
    public void filtroSemParametrosListaTudo() {
        List<Object> params = new ArrayList<Object>();
        assertEquals("SELECT * FROM despesas", DespesasDAO.montarFiltroMes(0, 0, 0, params));
        assertTrue(params.isEmpty());
    }

    @Test
    public void filtroCompletoUsaPlaceholdersNaOrdemCerta() {
        List<Object> params = new ArrayList<Object>();
        String sql = DespesasDAO.montarFiltroMes(3, 2016, 7, params);
        assertEquals("SELECT * FROM despesas WHERE MONTH(data_pag) = ? AND YEAR(data_pag) = ? AND cod_orindo = ?"
                + " ORDER BY data_desp DESC, descricao ASC", sql);
        assertEquals(Arrays.<Object>asList(3, 2016, 7), params);
    }

    @Test
    public void filtroParcialNaoConcatenaValores() {
        List<Object> params = new ArrayList<Object>();
        String sql = DespesasDAO.montarFiltroMes(0, 2015, 0, params);
        assertEquals("SELECT * FROM despesas WHERE YEAR(data_pag) = ? ORDER BY data_desp DESC, descricao ASC", sql);
        assertFalse(sql.contains("2015"));
        assertEquals(Arrays.<Object>asList(2015), params);
    }

    @Test
    public void gerarParcelasDaAtualAteAUltima() {
        Despesas base = despesa("Notebook", 2, 4, "2016-01-31");
        List<Despesas> parcelas = DespesasDAO.gerarParcelas(base);

        assertEquals(3, parcelas.size());
        assertEquals("Notebook P.:2", parcelas.get(0).getDescricao());
        assertEquals("Notebook P.:4", parcelas.get(2).getDescricao());
        assertEquals(Integer.valueOf(2), parcelas.get(0).getParcela());
        assertEquals(Integer.valueOf(4), parcelas.get(2).getParcela());
        // um mes por parcela, sem pular para marco quando o dia nao existe
        assertEquals("2016-01-31", parcelas.get(0).getData_pag().toString());
        assertEquals("2016-02-29", parcelas.get(1).getData_pag().toString());
        assertEquals("2016-03-29", parcelas.get(2).getData_pag().toString());
        // o objeto original nao e alterado
        assertEquals("Notebook", base.getDescricao());
        assertEquals(Integer.valueOf(2), base.getParcela());
        assertEquals("2016-01-31", base.getData_pag().toString());
    }

    @Test
    public void gerarParcelasCopiaOsDemaisCampos() {
        Despesas base = despesa("Curso", 1, 2, "2015-12-10");
        Despesas segunda = DespesasDAO.gerarParcelas(base).get(1);
        assertEquals("2016-01-10", segunda.getData_pag().toString());
        assertEquals(base.getValor(), segunda.getValor());
        assertEquals(base.getValor_parcela(), segunda.getValor_parcela());
        assertEquals(base.getCod_orindo(), segunda.getCod_orindo());
        assertEquals(base.getData_desp(), segunda.getData_desp());
        assertEquals("Aberto", segunda.getStatus());
    }

    @Test
    public void gerarParcelasComDataNula() {
        Despesas base = despesa("Sem data", 1, 2, null);
        List<Despesas> parcelas = DespesasDAO.gerarParcelas(base);
        assertEquals(2, parcelas.size());
        assertNull(parcelas.get(1).getData_pag());
    }

    static Despesas despesa(String descricao, int parcela, int parcelas, String dataPag) {
        Despesas d = new Despesas();
        d.setDescricao(descricao);
        d.setParcela(parcela);
        d.setParcelas(parcelas);
        d.setValor(300f);
        d.setValor_parcela(100f);
        d.setStatus("Aberto");
        d.setObs("");
        d.setCod_orindo(1);
        d.setCod_usuario(1);
        d.setData_desp(java.sql.Date.valueOf("2015-12-01"));
        d.setData_pag(dataPag == null ? null : java.sql.Date.valueOf(dataPag));
        return d;
    }
}
