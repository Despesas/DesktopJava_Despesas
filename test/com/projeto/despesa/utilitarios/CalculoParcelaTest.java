package com.projeto.despesa.utilitarios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CalculoParcelaTest {

    private static final double DELTA = 0.0001;

    @Test
    public void parseValorAceitaFormatoBrasileiro() {
        assertEquals(1234.56, CalculoParcela.parseValor("1.234,56"), DELTA);
        assertEquals(10.5, CalculoParcela.parseValor("10,50"), DELTA);
        assertEquals(1000000.0, CalculoParcela.parseValor(" 1.000.000,00 "), DELTA);
        assertEquals(7.0, CalculoParcela.parseValor("7"), DELTA);
    }

    @Test(expected = NumberFormatException.class)
    public void parseValorVazioFalha() {
        CalculoParcela.parseValor("  ");
    }

    @Test(expected = NumberFormatException.class)
    public void parseValorNuloFalha() {
        CalculoParcela.parseValor(null);
    }

    @Test(expected = NumberFormatException.class)
    public void parseValorComLetrasFalha() {
        CalculoParcela.parseValor("12,3a");
    }

    @Test
    public void formatarValorUsaPtBrIndependenteDoLocalePadrao() {
        java.util.Locale anterior = java.util.Locale.getDefault();
        try {
            java.util.Locale.setDefault(java.util.Locale.US);
            assertEquals("1.234,50", CalculoParcela.formatarValor(1234.5));
            assertEquals("0,00", CalculoParcela.formatarValor(0));
        } finally {
            java.util.Locale.setDefault(anterior);
        }
    }

    @Test
    public void formatarEParseSaoInversos() {
        double v = 98765.43;
        assertEquals(v, CalculoParcela.parseValor(CalculoParcela.formatarValor(v)), DELTA);
    }

    @Test
    public void valorParcelaArredondaParaCentavos() {
        assertEquals(33.33, CalculoParcela.valorParcela(100, 3), DELTA);
        assertEquals(50.0, CalculoParcela.valorParcela(100, 2), DELTA);
        assertEquals(100.0, CalculoParcela.valorParcela(100, 1), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void valorParcelaComZeroParcelasFalha() {
        CalculoParcela.valorParcela(100, 0);
    }

    @Test
    public void valorTotalMultiplicaParcelas() {
        assertEquals(99.99, CalculoParcela.valorTotal(33.33, 3), DELTA);
        assertEquals(0.3, CalculoParcela.valorTotal(0.1, 3), DELTA);
    }

    @Test
    public void parcelaValidaExigeIntervaloDeUmAteTotal() {
        assertTrue(CalculoParcela.parcelaValida(1, 1));
        assertTrue(CalculoParcela.parcelaValida(3, 10));
        assertTrue(CalculoParcela.parcelaValida(10, 10));
        assertFalse(CalculoParcela.parcelaValida(11, 10));
        assertFalse(CalculoParcela.parcelaValida(0, 10));
        assertFalse(CalculoParcela.parcelaValida(-1, 10));
        assertFalse(CalculoParcela.parcelaValida(1, 0));
    }

    @Test
    public void parcelasRestantesContaAtualSeAindaNaoVenceu() {
        assertEquals(8, CalculoParcela.parcelasRestantes(10, 3, false));
        assertEquals(7, CalculoParcela.parcelasRestantes(10, 3, true));
        assertEquals(1, CalculoParcela.parcelasRestantes(10, 10, false));
        assertEquals(0, CalculoParcela.parcelasRestantes(10, 10, true));
        assertEquals(0, CalculoParcela.parcelasRestantes(3, 5, true));
    }

    @Test
    public void valorRestante() {
        assertEquals(266.64, CalculoParcela.valorRestante(33.33, 8), DELTA);
        assertEquals(0.0, CalculoParcela.valorRestante(33.33, 0), DELTA);
    }

    @Test
    public void pagamentoUnicoQuandoSoRestaAParcelaAtual() {
        assertTrue(CalculoParcela.pagamentoUnico(1, 1));
        assertTrue(CalculoParcela.pagamentoUnico(12, 12));
        assertFalse(CalculoParcela.pagamentoUnico(1, 12));
    }

    @Test
    public void arredondarHalfEven() {
        assertEquals(0.12, CalculoParcela.arredondar(0.125), DELTA);
        assertEquals(0.14, CalculoParcela.arredondar(0.135), DELTA);
        assertEquals(10.0, CalculoParcela.arredondar(9.999), DELTA);
    }
}
