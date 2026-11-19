/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.event;

import com.projeto.despesa.dto.Usuario_server;
import com.projeto.despesa.dao.Usuario_serverDAO;
import com.projeto.despesa.form.Login;
import com.projeto.despesa.starter.StartPrincipal;
import com.projeto.despesa.utilitarios.ConfiguracaoBanco;
import com.projeto.despesa.utilitarios.Mensagens;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author rick.novaes
 */
public class EventLogin extends Login implements ActionListener {

  public EventLogin() {
    getInit();
  }

  private void getInit() {
    setVisible(true);
    Llogo.setOpaque(false);
    PpainelGeral.setOpaque(false);
    btLogar.addActionListener(this);
    btCancelar.addActionListener(this);
    tfSenha.addActionListener(this);
    tfUsuario.addActionListener(this);
    this.setIconImage(new ImageIcon(this.getClass().getResource("/com/projeto/despesa/images/TributsIco32.png")).getImage());
  }

  /** Converte o registro de usuario_server nas propriedades lidas por {@code conexao}. */
  public static Properties propriedadesSessao(Usuario_server user) {
    Properties properties = new Properties();
    properties.setProperty("url", valor(user.getUrl_server()));
    properties.setProperty("usuario", valor(user.getUser_server()));
    properties.setProperty("senha", valor(user.getSenha_server()));
    properties.setProperty("driver", ConfiguracaoBanco.DRIVER_PADRAO);
    properties.setProperty("porta.banco", valor(user.getPorta_server()));
    return properties;
  }

  private static String valor(String s) {
    return s == null ? "" : s;
  }

  /** Grava a conexao do usuario no arquivo de sessao (antes fixo em c://Despesas/lib). */
  private boolean setProperties(Usuario_server user) {
    File arquivo = ConfiguracaoBanco.arquivoSessao(ConfiguracaoBanco.carregar());
    File pasta = arquivo.getAbsoluteFile().getParentFile();
    if (pasta != null && !pasta.isDirectory() && !pasta.mkdirs()) {
      Mensagens.erro("Login", "Nao foi possivel criar " + pasta.getAbsolutePath(), null);
      return false;
    }
    try (OutputStream fos = new FileOutputStream(arquivo)) {
      propriedadesSessao(user).store(fos, "Propriedade usuario");
      return true;
    } catch (IOException ex) {
      Mensagens.erro("Login", "Nao foi possivel gravar " + arquivo.getAbsolutePath(), ex);
      return false;
    }
  }

  private boolean logar() {
    try {
      Usuario_server user = new Usuario_serverDAO().Login(tfUsuario.getText(), new String(tfSenha.getPassword()));
      // antes: user nulo (login invalido) gerava NullPointerException
      if (user != null && user.getCodigo() != null) {
        return setProperties(user);
      }
    } catch (SQLException ex) {
      Mensagens.erro("Login", "Erro ao consultar o banco de contas.", ex);
    } catch (IllegalStateException ex) {
      Mensagens.erro("Login", "Configuracao do banco ausente.", ex);
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
        StartPrincipal pr = new StartPrincipal();
        pr.setVisible(true);
        this.dispose();
      } else {
        JOptionPane.showMessageDialog(this, "Usuario/Senha Não Encontrado", "Login", JOptionPane.ERROR_MESSAGE);
      }
      tfUsuario.requestFocus();
    }
    if (e.getSource() == btCancelar) {
      System.exit(0);
    }
  }
}
