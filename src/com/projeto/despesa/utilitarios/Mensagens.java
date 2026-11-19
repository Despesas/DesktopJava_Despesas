package com.projeto.despesa.utilitarios;

import java.awt.GraphicsEnvironment;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Centraliza o tratamento de erros: registra no log (com stack trace) e, quando
 * houver interface grafica, mostra a mensagem ao usuario. Evita excecoes
 * engolidas em blocos catch vazios.
 */
public final class Mensagens {

    private static final Logger LOG = Logger.getLogger("com.projeto.despesa");

    private Mensagens() {
    }

    public static void erro(String titulo, String mensagem, Throwable causa) {
        LOG.log(Level.SEVERE, titulo + ": " + mensagem, causa);
        if (!GraphicsEnvironment.isHeadless()) {
            String detalhe = causa == null || causa.getMessage() == null ? "" : "\n" + causa.getMessage();
            JOptionPane.showMessageDialog(null, mensagem + detalhe, titulo, JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void info(String titulo, String mensagem) {
        LOG.info(titulo + ": " + mensagem);
        if (!GraphicsEnvironment.isHeadless()) {
            JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
