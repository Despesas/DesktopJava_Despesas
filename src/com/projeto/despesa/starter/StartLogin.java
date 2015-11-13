/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.starter;

import com.projeto.despesa.event.EventLogin;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author uhitlei.barbosa
 */
public class StartLogin extends EventLogin {

    public StartLogin() {
    }
    
     public static void StartNow() {

        WindowListener wl = new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                System.exit(0);
            }
        };

        StartLogin P = new StartLogin();
      //  P.setVisible(true);
        P.addWindowListener(wl);

    }

    
     public static void main(String[] args) {

        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        
         splash sScreem = new splash();
        new Thread() {

            @Override
            public void run() {
                int i = 0;
                while (i < 101) {

                    i++;
                    try {
                        sleep(20);
                    } catch (Exception ex) {
                    }
                }
                StartNow();

            }
        }.start();

                }
     
}
