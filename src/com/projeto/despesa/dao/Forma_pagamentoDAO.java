package com.projeto.despesa.dao;

import com.projeto.despesa.dto.Forma_pagamento;
import java.sql.*;
import java.util.*;
import com.projeto.despesa.utilitarios.conexao;
import javax.swing.*;

public class Forma_pagamentoDAO {

  public Forma_pagamentoDAO() {
    try {
      Class.forName(new conexao().config.getString("driver"));
    } catch (ClassNotFoundException ex) {
      JOptionPane.showMessageDialog(null, "Driver Erro: " + ex.getMessage(), "Construtor da Classe", 0);
      ex.printStackTrace();
    }
  }

  public void salvar(Forma_pagamento forma_pagamento) throws SQLException {
    Connection con = DriverManager.getConnection(new conexao().url, new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
    PreparedStatement ps = null;
    String sqlSalvar = "INSERT INTO forma_pagamento (nome, data_cadastro, data_alteracao ) VALUES (?, ?, ?)";
    try {
      ps = con.prepareStatement(sqlSalvar);
      ps.setString(1, forma_pagamento.getNome());
      ps.setDate(2, forma_pagamento.getData_cadastro());
      ps.setTimestamp(3, forma_pagamento.getData_alteracao());
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

  public void atualizar(Forma_pagamento forma_pagamento) throws SQLException {
    Connection con = DriverManager.getConnection(new conexao().url, new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
    PreparedStatement ps = null;
    String sqlAtualizar = "UPDATE forma_pagamento SET nome=?, data_cadastro=?, data_alteracao=? WHERE codigo=?";
    try {
      ps = con.prepareStatement(sqlAtualizar);
      ps.setString(1, forma_pagamento.getNome());
      ps.setDate(2, forma_pagamento.getData_cadastro());
      ps.setTimestamp(3, forma_pagamento.getData_alteracao());
      ps.setInt(4, forma_pagamento.getCodigo());
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

  public void excluir(Forma_pagamento forma_pagamento) throws SQLException {
    Connection con = DriverManager.getConnection(new conexao().url, new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
    PreparedStatement ps = null;
    String sqlExcluir = "DELETE FROM forma_pagamento WHERE codigo=?";
    try {
      ps = con.prepareStatement(sqlExcluir);
      ps.setInt(1, forma_pagamento.getCodigo());
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

  public Forma_pagamento localizar(Integer codigo) throws SQLException {
    Connection con = DriverManager.getConnection(new conexao().url, new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sqlLocalizar = "SELECT * FROM forma_pagamento WHERE codigo=?";
    Forma_pagamento forma_pagamento = new Forma_pagamento();
    try {
      ps = con.prepareStatement(sqlLocalizar);
      ps.setInt(1, codigo);
      rs = ps.executeQuery();
      //if(!rs.next()){
      //return null;
      //}
      forma_pagamento.setCodigo(rs.getInt("codigo"));
      forma_pagamento.setNome(rs.getString("nome"));
      forma_pagamento.setData_cadastro(rs.getDate("data_cadastro"));
      forma_pagamento.setData_alteracao(rs.getTimestamp("data_alteracao"));
      return forma_pagamento;
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
    return forma_pagamento;
  }

  public List<Forma_pagamento> listar() throws SQLException {
    List<Forma_pagamento> resultado = new ArrayList<Forma_pagamento>();
    //Connection con = DriverManager.getConnection(new conexao().url, new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
    Connection con = DriverManager.getConnection(new conexao().url, new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sqlListar = "SELECT * FROM forma_pagamento";
    Forma_pagamento forma_pagamento;
    try {
      ps = con.prepareStatement(sqlListar);
      rs = ps.executeQuery();
      //if(rs==null){
      //return null;
      //}
      while (rs.next()) {
        forma_pagamento = new Forma_pagamento();
        forma_pagamento.setCodigo(rs.getInt("codigo"));
        forma_pagamento.setNome(rs.getString("nome"));
        forma_pagamento.setData_cadastro(rs.getDate("data_cadastro"));
        forma_pagamento.setData_alteracao(rs.getTimestamp("data_alteracao"));
        resultado.add(forma_pagamento);
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