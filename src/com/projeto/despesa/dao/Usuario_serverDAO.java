package com.projeto.despesa.dao;

import com.java.geraDTO.classeGerada.bean.Usuario_server;
import java.sql.*;
import java.util.*;
import com.projeto.despesa.utilitarios.AccountConexao;
import javax.swing.*;

public class Usuario_serverDAO {

  public Usuario_serverDAO() {
    try {
      Class.forName(new AccountConexao().config.getString("driver"));
    } catch (ClassNotFoundException ex) {
      JOptionPane.showMessageDialog(null, "Driver Erro: " + ex.getMessage(), "Construtor da Classe", 0);
      ex.printStackTrace();
    }
  }

  public void salvar(Usuario_server usuario_server) throws SQLException {
    Connection con = DriverManager.getConnection(new AccountConexao().config.getString("url"), new AccountConexao().config.getString("usuario"), new AccountConexao().config.getString("senha"));
    PreparedStatement ps = null;
    String sqlSalvar = "INSERT INTO usuario_server (nome, user_sys, senha_sys, user_server, senha_server, porta_server, url_server ) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try {
      ps = con.prepareStatement(sqlSalvar);
      ps.setString(1, usuario_server.getNome());
      ps.setString(2, usuario_server.getUser_sys());
      ps.setString(3, usuario_server.getSenha_sys());
      ps.setString(4, usuario_server.getUser_server());
      ps.setString(5, usuario_server.getSenha_server());
      ps.setString(6, usuario_server.getPorta_server());
      ps.setString(7, usuario_server.getUrl_server());
      ps.executeUpdate();
      JOptionPane.showMessageDialog(null, "Inserido Com Sucesso: ", "Mensagem do Sistema - Salvar", 1);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "NumberFormaterExeption Erro: " + e.getMessage(), "ClasseDAO Func.Salvar", 0);
      e.printStackTrace();
    } catch (NullPointerException e) {
      JOptionPane.showMessageDialog(null, "NullPointerException Erro: " + e.getMessage(), "ClasseDAO Func. Salvar", 0);
      e.printStackTrace();
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null, "SQLException Erro: " + e.getMessage(), "ClasseDAO Func. Salvar", 0);
      e.printStackTrace();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Exception Erro: " + e.getMessage(), "ClasseDAO Func. Salvar", 0);
      e.printStackTrace();
    } finally {
      ps.close();
      con.close();
    }
  }

  public void atualizar(Usuario_server usuario_server) throws SQLException {
    Connection con = DriverManager.getConnection(new AccountConexao().config.getString("url"), new AccountConexao().config.getString("usuario"), new AccountConexao().config.getString("senha"));
    PreparedStatement ps = null;
    String sqlAtualizar = "UPDATE usuario_server SET nome=?, user_sys=?, senha_sys=?, user_server=?, senha_server=?, porta_server=?, url_server=? WHERE codigo=?";
    try {
      ps = con.prepareStatement(sqlAtualizar);
      ps.setString(1, usuario_server.getNome());
      ps.setString(2, usuario_server.getUser_sys());
      ps.setString(3, usuario_server.getSenha_sys());
      ps.setString(4, usuario_server.getUser_server());
      ps.setString(5, usuario_server.getSenha_server());
      ps.setString(6, usuario_server.getPorta_server());
      ps.setString(7, usuario_server.getUrl_server());
      ps.setInt(8, usuario_server.getCodigo());
      ps.executeUpdate();
      JOptionPane.showMessageDialog(null, "Atualizado Com Sucesso: ", "Mensagem do Sistema - Atualizar", 1);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "NumberFormaterExeption Erro: " + e.getMessage(), "ClasseDAO Func.Atualizar", 0);
      e.printStackTrace();
    } catch (NullPointerException e) {
      JOptionPane.showMessageDialog(null, "NullPointerException Erro: " + e.getMessage(), "ClasseDAO Func. Atualizar", 0);
      e.printStackTrace();
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null, "SQLException Erro: " + e.getMessage(), "ClasseDAO Func. Atualizar", 0);
      e.printStackTrace();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Exception Erro: " + e.getMessage(), "ClasseDAO Func. Atualizar", 0);
      e.printStackTrace();
    } finally {
      ps.close();
      con.close();
    }
  }

  public void excluir(Usuario_server usuario_server) throws SQLException {
    Connection con = DriverManager.getConnection(new AccountConexao().config.getString("url"), new AccountConexao().config.getString("usuario"), new AccountConexao().config.getString("senha"));
    PreparedStatement ps = null;
    String sqlExcluir = "DELETE FROM usuario_server WHERE codigo=?";
    try {
      ps = con.prepareStatement(sqlExcluir);
      ps.setInt(1, usuario_server.getCodigo());
      ps.executeUpdate();
      JOptionPane.showMessageDialog(null, "Ecluido Com Sucesso: ", "Mensagem do Sistema - Excluir", 1);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "NumberFormaterExeption Erro: " + e.getMessage(), "ClasseDAO Func.Excluir", 0);
      e.printStackTrace();
    } catch (NullPointerException e) {
      JOptionPane.showMessageDialog(null, "NullPointerException Erro: " + e.getMessage(), "ClasseDAO Func. Excluir", 0);
      e.printStackTrace();
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null, "SQLException Erro: " + e.getMessage(), "ClasseDAO Func. Excluir", 0);
      e.printStackTrace();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Exception Erro: " + e.getMessage(), "ClasseDAO Func. Excluir", 0);
      e.printStackTrace();
    } finally {
      ps.close();
      con.close();
    }
  }

  public Usuario_server Login(String usuario, String senha) throws SQLException {
    Connection con = DriverManager.getConnection(new AccountConexao().config.getString("url"), new AccountConexao().config.getString("usuario"), new AccountConexao().config.getString("senha"));
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sqlLocalizar = "SELECT * FROM usuario_server WHERE user_sys=? and senha_sys=?";
    Usuario_server usuario_server = new Usuario_server();
    try {
      ps = con.prepareStatement(sqlLocalizar);
      ps.setString(1, usuario);
      ps.setString(2, senha);
      rs = ps.executeQuery();
    if(!rs.next()){
      return null;
     }
      usuario_server.setCodigo(rs.getInt("codigo"));
      usuario_server.setNome(rs.getString("nome"));
      usuario_server.setUser_sys(rs.getString("user_sys"));
      usuario_server.setSenha_sys(rs.getString("senha_sys"));
      usuario_server.setUser_server(rs.getString("user_server"));
      usuario_server.setSenha_server(rs.getString("senha_server"));
      usuario_server.setPorta_server(rs.getString("porta_server"));
      usuario_server.setUrl_server(rs.getString("url_server"));
      return usuario_server;
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "NumberFormaterExeption Erro: " + e.getMessage(), "ClasseDAO Func.Localizar", 0);
      e.printStackTrace();
    } catch (NullPointerException e) {
      JOptionPane.showMessageDialog(null, "NullPointerException Erro: " + e.getMessage(), "ClasseDAO Func. Localizar", 0);
      e.printStackTrace();
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null, "SQLException Erro: " + e.getMessage(), "ClasseDAO Func. Localizar", 0);
      e.printStackTrace();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Exception Erro: " + e.getMessage(), "ClasseDAO Func. Localizar", 0);
      e.printStackTrace();
    } finally {
      ps.close();
      con.close();
    }
    return usuario_server;
  }

  public Usuario_server localizar(Integer codigo) throws SQLException {
    Connection con = DriverManager.getConnection(new AccountConexao().config.getString("url"), new AccountConexao().config.getString("usuario"), new AccountConexao().config.getString("senha"));
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sqlLocalizar = "SELECT * FROM usuario_server WHERE codigo=?";
    Usuario_server usuario_server = new Usuario_server();
    try {
      ps = con.prepareStatement(sqlLocalizar);
      ps.setInt(1, codigo);
      rs = ps.executeQuery();
      //if(!rs.next()){
      //return null;
      //}
      usuario_server.setCodigo(rs.getInt("codigo"));
      usuario_server.setNome(rs.getString("nome"));
      usuario_server.setUser_sys(rs.getString("user_sys"));
      usuario_server.setSenha_sys(rs.getString("senha_sys"));
      usuario_server.setUser_server(rs.getString("user_server"));
      usuario_server.setSenha_server(rs.getString("senha_server"));
      usuario_server.setPorta_server(rs.getString("porta_server"));
      usuario_server.setUrl_server(rs.getString("url_server"));
      return usuario_server;
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "NumberFormaterExeption Erro: " + e.getMessage(), "ClasseDAO Func.Localizar", 0);
      e.printStackTrace();
    } catch (NullPointerException e) {
      JOptionPane.showMessageDialog(null, "NullPointerException Erro: " + e.getMessage(), "ClasseDAO Func. Localizar", 0);
      e.printStackTrace();
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null, "SQLException Erro: " + e.getMessage(), "ClasseDAO Func. Localizar", 0);
      e.printStackTrace();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Exception Erro: " + e.getMessage(), "ClasseDAO Func. Localizar", 0);
      e.printStackTrace();
    } finally {
      ps.close();
      con.close();
    }
    return usuario_server;
  }

  public List<Usuario_server> listar() throws SQLException {
    List<Usuario_server> resultado = new ArrayList<Usuario_server>();
    Connection con = DriverManager.getConnection(new AccountConexao().config.getString("url"), new AccountConexao().config.getString("usuario"), new AccountConexao().config.getString("senha"));
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sqlListar = "SELECT * FROM usuario_server";
    Usuario_server usuario_server;
    try {
      ps = con.prepareStatement(sqlListar);
      rs = ps.executeQuery();
      //if(rs==null){
      //return null;
      //}
      while (rs.next()) {
        usuario_server = new Usuario_server();
        usuario_server.setCodigo(rs.getInt("codigo"));
        usuario_server.setNome(rs.getString("nome"));
        usuario_server.setUser_sys(rs.getString("user_sys"));
        usuario_server.setSenha_sys(rs.getString("senha_sys"));
        usuario_server.setUser_server(rs.getString("user_server"));
        usuario_server.setSenha_server(rs.getString("senha_server"));
        usuario_server.setPorta_server(rs.getString("porta_server"));
        usuario_server.setUrl_server(rs.getString("url_server"));
        resultado.add(usuario_server);
      }
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "NumberFormaterExeption Erro: " + e.getMessage(), "ClasseDAO Func.Localizar", 0);
      e.printStackTrace();
    } catch (NullPointerException e) {
      JOptionPane.showMessageDialog(null, "NullPointerException Erro: " + e.getMessage(), "ClasseDAO Func. Localizar", 0);
      e.printStackTrace();
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null, "SQLException Erro: " + e.getMessage(), "ClasseDAO Func. Localizar", 0);
      e.printStackTrace();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Exception Erro: " + e.getMessage(), "ClasseDAO Func. Localizar", 0);
      e.printStackTrace();
    } finally {
      ps.close();
      con.close();
    }
    return resultado;
  }
}