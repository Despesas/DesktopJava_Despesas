package com.projeto.despesa.dao;

import com.projeto.despesa.dto.Despesas_pagamento;
import java.sql.*;
import java.util.*;
import com.projeto.despesa.utilitarios.conexao;
import javax.swing.*;

public class Despesas_pagamentoDAO {

  public Despesas_pagamentoDAO() {
    try {
      Class.forName(new conexao().config.getString("driver"));
    } catch (ClassNotFoundException ex) {
      JOptionPane.showMessageDialog(null, "Driver Erro: " + ex.getMessage(), "Construtor da Classe", 0);
      ex.printStackTrace();
    }
  }

  public void salvar(Despesas_pagamento despesas_pagamento) throws SQLException {
    Connection con = DriverManager.getConnection(new conexao().config.getString("url"), new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
    PreparedStatement ps = null;
    String sqlSalvar = "INSERT INTO despesas_pagamento (despesas_codigo, data_pagamento ) VALUES (?, ?)";
    try {
      ps = con.prepareStatement(sqlSalvar);
      ps.setInt(1, despesas_pagamento.getDespesas_codigo());
      ps.setTimestamp(2, despesas_pagamento.getData_pagamento());
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

  public void atualizar(Despesas_pagamento despesas_pagamento) throws SQLException {
    Connection con = DriverManager.getConnection(new conexao().config.getString("url"), new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
    PreparedStatement ps = null;
    String sqlAtualizar = "UPDATE despesas_pagamento SET despesas_codigo=?, data_pagamento=? WHERE forma_pagamento_codigo=?";
    try {
      ps = con.prepareStatement(sqlAtualizar);
      ps.setInt(1, despesas_pagamento.getDespesas_codigo());
      ps.setTimestamp(2, despesas_pagamento.getData_pagamento());
      ps.setInt(3, despesas_pagamento.getForma_pagamento_codigo());
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

  public void excluir(Despesas_pagamento despesas_pagamento) throws SQLException {
    Connection con = DriverManager.getConnection(new conexao().config.getString("url"), new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
    PreparedStatement ps = null;
    String sqlExcluir = "DELETE FROM despesas_pagamento WHERE forma_pagamento_codigo=?";
    try {
      ps = con.prepareStatement(sqlExcluir);
      ps.setInt(1, despesas_pagamento.getForma_pagamento_codigo());
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

  public Despesas_pagamento localizar(Integer forma_pagamento_codigo) throws SQLException {
    Connection con = DriverManager.getConnection(new conexao().config.getString("url"), new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sqlLocalizar = "SELECT * FROM despesas_pagamento WHERE forma_pagamento_codigo=?";
    Despesas_pagamento despesas_pagamento = new Despesas_pagamento();
    try {
      ps = con.prepareStatement(sqlLocalizar);
      ps.setInt(1, forma_pagamento_codigo);
      rs = ps.executeQuery();
      //if(!rs.next()){
      //return null;
      //}
      despesas_pagamento.setForma_pagamento_codigo(rs.getInt("forma_pagamento_codigo"));
      despesas_pagamento.setDespesas_codigo(rs.getInt("despesas_codigo"));
      despesas_pagamento.setData_pagamento(rs.getTimestamp("data_pagamento"));
      return despesas_pagamento;
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
    return despesas_pagamento;
  }

  public List<Despesas_pagamento> listar() throws SQLException {
    List<Despesas_pagamento> resultado = new ArrayList<Despesas_pagamento>();
    Connection con = DriverManager.getConnection(new conexao().config.getString("url"), new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sqlListar = "SELECT * FROM despesas_pagamento";
    Despesas_pagamento despesas_pagamento;
    try {
      ps = con.prepareStatement(sqlListar);
      rs = ps.executeQuery();
      //if(rs==null){
      //return null;
      //}
      while (rs.next()) {
        despesas_pagamento = new Despesas_pagamento();
        despesas_pagamento.setForma_pagamento_codigo(rs.getInt("forma_pagamento_codigo"));
        despesas_pagamento.setDespesas_codigo(rs.getInt("despesas_codigo"));
        despesas_pagamento.setData_pagamento(rs.getTimestamp("data_pagamento"));
        resultado.add(despesas_pagamento);
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