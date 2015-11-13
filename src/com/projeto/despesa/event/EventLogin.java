/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.event;

import com.java.geraDTO.classeGerada.bean.Usuario_server;
import com.projeto.despesa.dao.Usuario_serverDAO;
import com.projeto.despesa.form.Login;
import com.projeto.despesa.starter.StartPrincipal;
import com.projeto.despesa.utilitarios.conexao;
import com.sun.awt.AWTUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author rick.novaes
 */
public class EventLogin extends Login implements ActionListener {

  List<Usuario_server> UserList;

  public EventLogin() {
    getInit();

  }

  private void getInit() {
//        AWTUtilities.setWindowOpacity(this, 0.7F);
    setVisible(true);
    Llogo.setOpaque(false);
    PpainelGeral.setOpaque(false);
    btLogar.addActionListener(this);
    btCancelar.addActionListener(this);
    tfSenha.addActionListener(this);
    tfUsuario.addActionListener(this);
    this.setIconImage(new ImageIcon(this.getClass().getResource("/com/projeto/despesa/images/TributsIco32.png")).getImage());
    try {
      UserList = new Usuario_serverDAO().listar();
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, "SQLException Erro: " + ex.getMessage(), "EventLogin Func: getInit()", 0);
    }


  }

  private void setProperties(Usuario_server user) {


    Properties properties = new Properties();
    properties.setProperty("url", user.getUrl_server());
    properties.setProperty("usuario", user.getUser_server());
    properties.setProperty("senha", user.getSenha_server());
    properties.setProperty("driver", "com.mysql.jdbc.Driver");
    properties.setProperty("porta.banco", user.getPorta_server());

    try {
//            String local = config.getClass().getResource("com/projeto/despesa/utilitarios/conexao.java").toString()+"/bancodedados.properties";
//            System.out.println(local);
      //Criamos um objeto FileOutputStream             
      FileOutputStream fos = new FileOutputStream("c://Despesas/lib/bancodedados.properties"); //"c://Despesas/lib/bancodedados.properties"
      //grava os dados no arquivo
      properties.store(fos, "Propriedade usuario");
      //fecha o arquivo
      fos.close();
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
      ex.printStackTrace();
    }



  }

  private boolean logar() {
    try {
      Usuario_server user = new Usuario_serverDAO().Login(tfUsuario.getText(), tfSenha.getText());
      
      if (user.getCodigo()!= null) {
  //      for (int i = 0; i < UserList.size(); i++) {
  //        if (UserList.get(i).getUser_sys().equals(tfUsuario.getText())) {
  //          if (UserList.get(i).getSenha_sys().equals(tfSenha.getText())) {
             setProperties(user);
             return true;
  //          }
  //        }
  //      }
      }

      
    } catch (SQLException ex) {
       JOptionPane.showMessageDialog(null, "Boolean Error: \n Usuario/Senha Não Encontrado.", "ClasseDAO Func. Salvar", 0);
      Logger.getLogger(EventLogin.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
    return false;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == tfUsuario) {
      tfSenha.requestFocus();
    }
    if (e.getSource() == btLogar || e.getSource() == tfSenha) {
      if (logar()) {
//                System.out.println(new conexao().config.getString("url"));
        StartPrincipal pr = new StartPrincipal();
        pr.setVisible(true);
        this.dispose();
      } else {
        JOptionPane.showMessageDialog(null, "Exception Erro: \n Usuario/Senha Não Encontrado", "ClasseDAO Func. Salvar", 0);
      }
      tfUsuario.requestFocus();
    }
    if (e.getSource() == btCancelar) {
      System.exit(0);
    }
  }
}
