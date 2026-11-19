package com.projeto.despesa.utilitarios;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import javax.swing.text.BadLocationException;
import org.junit.Test;

public class FormatarCampoTest {

    @Test
    public void getNumBooleanComparaConteudoENaoReferencia() {
        FormatarCampo f = new FormatarCampo();
        assertEquals(1, f.getNumBoolean("SIM"));
        // antes "==" so funcionava com a string literal
        assertEquals(1, f.getNumBoolean(new String("SIM")));
        assertEquals(0, f.getNumBoolean("NAO"));
        assertEquals(0, f.getNumBoolean(null));
    }

    @Test
    public void numberFormatUsaPadraoBrasileiro() {
        assertEquals("1.234,50", new FormatarCampo().numberFormat.format(1234.5));
    }

    @Test
    public void mascarasDeInteiros() {
        FormatarCampo f = new FormatarCampo();
        assertEquals("####", f.getIntAno().getMask());
        // antes getIntAno sobrescrevia a mascara de tres digitos
        assertEquals("###", f.getIntTresDigt().getMask());
        assertEquals("##", f.getIntDoisDigt().getMask());
    }

    @Test
    public void dateDocumentInsereBarrasAoDigitar() throws BadLocationException {
        DateDocument doc = new DateDocument();
        digitar(doc, "15032015");
        assertEquals("15/03/2015", doc.getText(0, doc.getLength()));
        assertEquals("15/03/2015", new SimpleDateFormat("dd/MM/yyyy").format(doc.getDate()));
    }

    @Test
    public void dateDocumentNaoPassaDeDezCaracteres() throws BadLocationException {
        DateDocument doc = new DateDocument();
        digitar(doc, "15032015");
        doc.insertString(doc.getLength(), "9", null);
        doc.insertString(doc.getLength(), "9", null);
        assertEquals(10, doc.getLength());
    }

    @Test
    public void dateDocumentSetDate() throws BadLocationException {
        DateDocument doc = new DateDocument();
        doc.setDate(java.sql.Date.valueOf("2016-02-29"));
        assertEquals("29/02/2016", doc.getText(0, doc.getLength()));
        doc.setDate(null);
        assertEquals(0, doc.getLength());
    }

    private static void digitar(DateDocument doc, String digitos) throws BadLocationException {
        for (char c : digitos.toCharArray()) {
            doc.insertString(doc.getLength(), String.valueOf(c), null);
        }
    }
}
