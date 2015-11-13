package com.projeto.despesa.dao;

import com.projeto.despesa.dto.Despesas;
import com.projeto.despesa.utilitarios.Data;
import java.sql.*;
import java.util.*;
import com.projeto.despesa.utilitarios.conexao;
import javax.swing.*;

public class DespesasDAO {

  public DespesasDAO() {
    try {
      Class.forName(new conexao().config.getString("driver"));
    } catch (ClassNotFoundException ex) {
      JOptionPane.showMessageDialog(null, "Driver Erro: " + ex.getMessage(), "Construtor da Classe", 0);
      ex.printStackTrace();
    }
  }

  public void salvar(Despesas despesas) throws SQLException {
    Connection con = DriverManager.getConnection(new conexao().url, new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
    PreparedStatement ps = null;
    String sqlSalvar = "INSERT INTO despesas (descricao, data_desp, data_pag, parcelas, parcela, valor_parcela, valor, status, obs, cod_orindo, cod_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
      ps = con.prepareStatement(sqlSalvar);
      ps.setString(1, despesas.getDescricao());
      ps.setDate(2, despesas.getData_desp());
      ps.setDate(3, despesas.getData_pag());
      ps.setInt(4, despesas.getParcelas());
      ps.setInt(5, despesas.getParcela());
      ps.setFloat(6, despesas.getValor_parcela());
      ps.setFloat(7, despesas.getValor());
      ps.setString(8, despesas.getStatus());
      ps.setString(9, despesas.getObs());
      ps.setInt(10, despesas.getCod_orindo());
      ps.setInt(11, despesas.getCod_usuario());
//            ps.setFloat(12, despesas.getValor_restante()); 
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

  public void salvarParcelado(Despesas despesas) throws SQLException {
    Connection con = DriverManager.getConnection(new conexao().url, new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
    PreparedStatement ps = null;
    String sqlSalvar = "INSERT INTO despesas (descricao, data_desp, data_pag, parcelas, parcela, valor_parcela, valor, status, obs, cod_orindo, cod_usuario ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {
      ps = con.prepareStatement(sqlSalvar);
//            setPStatment(ps, despesas);
//            ps.executeUpdate();
      String Descricao = despesas.getDescricao();
      despesas.setDescricao(Descricao + " P.:" + (despesas.getParcela()));
      setPStatment(ps, despesas);
      ps.executeUpdate();
      for (int Parcela = despesas.getParcela() + 1; despesas.getParcelas() - despesas.getParcela() > 0; Parcela++) {
        despesas.setParcela(Parcela);
        despesas.setDescricao(Descricao + " P.:" + Parcela);
        String dataProxima = new Data().getProximoMes(despesas.getData_pag());
        despesas.setData_pag(new Data().getDataBanco(dataProxima));
        setPStatment(ps, despesas);
        ps.executeUpdate();
      }


      JOptionPane.showMessageDialog(null, "Inserido Parcelas Com Sucesso: ", "Mensagem do Sistema - Salvar", 1);
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

  private void setPStatment(PreparedStatement ps, Despesas despesas) throws SQLException {
    ps.setString(1, despesas.getDescricao());
    ps.setDate(2, despesas.getData_desp());
    ps.setDate(3, despesas.getData_pag());
    ps.setInt(4, despesas.getParcelas());
    ps.setInt(5, despesas.getParcela());
    ps.setFloat(6, despesas.getValor_parcela());
    ps.setFloat(7, despesas.getValor());
    ps.setString(8, despesas.getStatus());
    ps.setString(9, despesas.getObs());
    ps.setInt(10, despesas.getCod_orindo());
    ps.setInt(11, despesas.getCod_usuario());
    //  ps.setFloat(12, despesas.getValor_restante()); 
  }

  public void atualizar(Despesas despesas) throws SQLException {
    Connection con = DriverManager.getConnection(new conexao().url, new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
    PreparedStatement ps = null;
    String sqlAtualizar = "UPDATE despesas SET descricao=?, data_desp=?, data_pag=?, parcelas=?, parcela=?, valor_parcela=?, valor=?, status=?, obs=?, cod_orindo=?, cod_usuario=? WHERE codigo=?";
    try {
      ps = con.prepareStatement(sqlAtualizar);
      ps.setString(1, despesas.getDescricao());
      ps.setDate(2, despesas.getData_desp());
      ps.setDate(3, despesas.getData_pag());
      ps.setInt(4, despesas.getParcelas());
      ps.setInt(5, despesas.getParcela());
      ps.setFloat(6, despesas.getValor_parcela());
      ps.setFloat(7, despesas.getValor());
      ps.setString(8, despesas.getStatus());
      ps.setString(9, despesas.getObs());
      ps.setInt(10, despesas.getCod_orindo());
      ps.setInt(11, despesas.getCod_usuario());
      //     ps.setFloat(12, despesas.getValor_restante()); 
      ps.setInt(12, despesas.getCodigo());

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

  public void excluir(Despesas despesas) throws SQLException {
    Connection con = DriverManager.getConnection(new conexao().url, new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
    PreparedStatement ps = null;
    String sqlExcluir = "DELETE FROM despesas WHERE codigo=?";
    try {
      ps = con.prepareStatement(sqlExcluir);
      ps.setInt(1, despesas.getCodigo());
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

  public Despesas localizar(Integer codigo) throws SQLException {
    Connection con = DriverManager.getConnection(new conexao().url, new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sqlLocalizar = "SELECT * FROM despesas WHERE codigo=?";
    Despesas despesas = new Despesas();
    try {
      ps = con.prepareStatement(sqlLocalizar);
      ps.setInt(1, codigo);
      rs = ps.executeQuery();
      if (!rs.next()) {
        return null;
      }
      despesas.setCodigo(rs.getInt("codigo"));
      despesas.setDescricao(rs.getString("descricao"));
      despesas.setData_desp(rs.getDate("data_desp"));
      despesas.setData_pag(rs.getDate("data_pag"));
      despesas.setParcelas(rs.getInt("parcelas"));
      despesas.setParcela(rs.getInt("parcela"));
      despesas.setValor_parcela(rs.getFloat("valor_parcela"));
      despesas.setValor(rs.getFloat("valor"));
      despesas.setStatus(rs.getString("status"));
      despesas.setObs(rs.getString("obs"));
      despesas.setCod_orindo(rs.getInt("cod_orindo"));
      despesas.setCod_usuario(rs.getInt("cod_usuario"));
      // despesas.setValor_restante(rs.getFloat("valor_restante"));

      return despesas;
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
    return despesas;
  }

  public List<Despesas> listar() throws SQLException {
    List<Despesas> resultado = new ArrayList<Despesas>();
    Connection con = DriverManager.getConnection(new conexao().url, new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sqlListar = "SELECT * FROM despesas ORDER BY descricao";
    Despesas despesas;
    try {
      ps = con.prepareStatement(sqlListar);
      rs = ps.executeQuery();
      //if(rs==null){
      //return null;
      //}
      while (rs.next()) {
        despesas = new Despesas();
        despesas.setCodigo(rs.getInt("codigo"));
        despesas.setDescricao(rs.getString("descricao"));
        despesas.setData_desp(rs.getDate("data_desp"));
        despesas.setData_pag(rs.getDate("data_pag"));
        despesas.setParcelas(rs.getInt("parcelas"));
        despesas.setParcela(rs.getInt("parcela"));
        despesas.setValor_parcela(rs.getFloat("valor_parcela"));
        despesas.setValor(rs.getFloat("valor"));
        despesas.setStatus(rs.getString("status"));
        despesas.setObs(rs.getString("obs"));
        despesas.setCod_orindo(rs.getInt("cod_orindo"));
        despesas.setCod_usuario(rs.getInt("cod_usuario"));
        //  despesas.setValor_restante(rs.getFloat("valor_restante"));
        resultado.add(despesas);
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

  public List<Despesas> listarByMes(int mes, int ano, int cod_o) throws SQLException {
    List<Despesas> resultado = new ArrayList<Despesas>();
    Connection con = DriverManager.getConnection(new conexao().url, new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sqlListar = "SELECT * FROM despesas";
    Despesas despesas;
    int verf = 0;

    if (mes > 0 || ano > 0 || cod_o > 0) {
      sqlListar = sqlListar + " WHERE";
      if (mes > 0) {// mes=s, ano=x cod_o=x
        if (verf == 0) {
          verf++;
          sqlListar = sqlListar + " MONTH(data_pag)=" + mes;
        } else {
          sqlListar = sqlListar + " AND MONTH(data_pag)=" + mes;
        }
      }

      if (ano != 0) {
        if (verf == 0) {
          verf++;
          sqlListar = sqlListar + " YEAR(data_pag)=" + ano;
        } else {
          sqlListar = sqlListar + " AND YEAR(data_pag)=" + ano;
        }
      }
      if (cod_o > 0) {// mes=s, ano=s cod_o=s
        if (verf == 0) {
          verf++;
          sqlListar = sqlListar + " cod_orindo = " + cod_o;
        } else {
          sqlListar = sqlListar + " AND cod_orindo = " + cod_o;// mes=s, ano=s cod_o=s
        }

      }


      sqlListar = sqlListar + " ORDER BY data_desp DESC, descricao ASC";

    }
    System.out.println(sqlListar);

    try {
      ps = con.prepareStatement(sqlListar);
      rs = ps.executeQuery();
      //if(rs==null){
      //return null;
      //}
      while (rs.next()) {
        despesas = new Despesas();
        despesas.setCodigo(rs.getInt("codigo"));
        despesas.setDescricao(rs.getString("descricao"));
        despesas.setData_desp(rs.getDate("data_desp"));
        despesas.setData_pag(rs.getDate("data_pag"));
        despesas.setParcelas(rs.getInt("parcelas"));
        despesas.setParcela(rs.getInt("parcela"));
        despesas.setValor_parcela(rs.getFloat("valor_parcela"));
        despesas.setValor(rs.getFloat("valor"));
        despesas.setStatus(rs.getString("status"));
        despesas.setObs(rs.getString("obs"));
        despesas.setCod_orindo(rs.getInt("cod_orindo"));
        despesas.setCod_usuario(rs.getInt("cod_usuario"));
        despesas.setSql(sqlListar);
        resultado.add(despesas);
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
  
  public List<String> listAnoByDespesas() throws SQLException {
    List<String> resultado = new ArrayList();
    Connection con = DriverManager.getConnection(new conexao().url, new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sqlListar = "select YEAR(despesas.data_pag) as Ano from despesas\n" +
                       "group by YEAR(despesas.data_pag) ";
   
    

    try {
      ps = con.prepareStatement(sqlListar);
      rs = ps.executeQuery();
      //if(rs==null){
      //return null;
      //}
      while (rs.next()) {
        resultado.add(rs.getString("Ano"));
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