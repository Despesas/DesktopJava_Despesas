package com.projeto.despesa.starter;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * 
 */
public class splash extends JWindow {

    AbsoluteLayout absoluto;
    AbsoluteConstraints absimage, absbarra;
    ImageIcon image;
    JLabel jlabel;
    JProgressBar barra;
    int i = 0;
    boolean StartTela = false;
    JPanel panel;

    public splash() {
        com.sun.awt.AWTUtilities.setWindowOpaque(this, false);
        absoluto = new AbsoluteLayout();
        absimage = new AbsoluteConstraints(0, 0);
        absbarra = new AbsoluteConstraints(-50, 125); // POSISAO BARRA
        jlabel = new JLabel();

        image = new ImageIcon(this.getClass().getResource("/com/projeto/despesa/images/splash1.png"));

        jlabel.setIcon(image);
        //jlabel.setBackground(Color.BLACK);

        barra = new JProgressBar();
        barra.setPreferredSize(new Dimension(180, 10));//TAMANHO BARRA
        // this.setBackground(Color.BLACK);

//        panel = new JPanel() {
//
//            @Override
//            public void paintComponent(Graphics grh) {
//                Paint p = new Color(0, 255, 0, 0);
//                Graphics2D g2d = (Graphics2D) grh;
//                g2d.setPaint(p);
//            }
//        };
//
//        panel.add(jlabel);

        this.getContentPane().setLayout(absoluto);
        
        this.getContentPane().add(jlabel, absimage);
        this.getContentPane().add(barra, absbarra);

        new Thread() {

            public void run() {

                while (i < 101) {
                    barra.setValue(i);
                    i++;
                    try {
                        sleep(20);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(splash.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                disposeIt();

            }
        }.start();
        this.setSize(image.getIconWidth(), image.getIconHeight());
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void disposeIt() {
       this.dispose();

    }
//    public static void main(String args[]) {
//        new splash();
//    }
}
