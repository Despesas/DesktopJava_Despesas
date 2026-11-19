package com.projeto.despesa.utilitarios;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Regras de calculo de valores e parcelas usadas pelas telas de despesa.
 * Extraidas dos eventos Swing para poderem ser testadas sem interface.
 */
public final class CalculoParcela {

    /** Os valores sao digitados e exibidos no formato brasileiro (1.234,56). */
    public static final Locale PT_BR = new Locale("pt", "BR");

    private CalculoParcela() {
    }

    /** Formatador com duas casas decimais em pt-BR. */
    public static NumberFormat formatoMoeda() {
        NumberFormat nf = NumberFormat.getInstance(PT_BR);
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        return nf;
    }

    /** Converte "1.234,56" (ou "1234,56") em 1234.56. */
    public static double parseValor(String texto) {
        if (texto == null || texto.trim().length() == 0) {
            throw new NumberFormatException("Valor nao informado");
        }
        String s = texto.trim().replace(".", "").replace(",", ".");
        return Double.parseDouble(s);
    }

    /** Formata 1234.5 como "1.234,50". */
    public static String formatarValor(double valor) {
        return formatoMoeda().format(valor);
    }

    /** Arredonda para centavos (HALF_EVEN, o mesmo modo do NumberFormat usado antes). */
    public static double arredondar(double valor) {
        return new BigDecimal(Double.toString(valor)).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }

    /** Parcela atual precisa estar entre 1 e a quantidade de parcelas. */
    public static boolean parcelaValida(int parcela, int parcelas) {
        return parcela >= 1 && parcelas >= 1 && parcela <= parcelas;
    }

    /** Valor de cada parcela a partir do valor total. */
    public static double valorParcela(double valorTotal, int parcelas) {
        if (parcelas <= 0) {
            throw new IllegalArgumentException("Quantidade de parcelas deve ser maior que zero");
        }
        return arredondar(valorTotal / parcelas);
    }

    /** Valor total a partir do valor da parcela. */
    public static double valorTotal(double valorParcela, int parcelas) {
        return arredondar(valorParcela * parcelas);
    }

    /**
     * Quantidade de parcelas que ainda faltam pagar, contando a atual
     * enquanto a data de pagamento dela nao chegou.
     */
    public static int parcelasRestantes(int parcelas, int parcelaAtual, boolean parcelaAtualVencida) {
        int restantes = parcelas - parcelaAtual + (parcelaAtualVencida ? 0 : 1);
        return Math.max(0, restantes);
    }

    /** Valor que ainda falta pagar. */
    public static double valorRestante(double valorParcela, int parcelasRestantes) {
        return arredondar(valorParcela * parcelasRestantes);
    }

    /** Indica se a despesa e lancada em uma unica vez (so resta a parcela atual). */
    public static boolean pagamentoUnico(int parcela, int parcelas) {
        return (parcelas - parcela) + 1 == 1;
    }
}
