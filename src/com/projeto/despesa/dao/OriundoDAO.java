package com.projeto.despesa.dao;

import com.projeto.despesa.dto.Oriundo;
import com.projeto.despesa.utilitarios.conexao;
import java.sql.*;
import java.util.*;

import javax.swing.*;

public class OriundoDAO {

    public OriundoDAO() {
        try {
            Class.forName(new conexao().config.getString("driver"));
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Driver Erro: " + ex.getMessage(), "Construtor da Classe", 0);
            ex.printStackTrace();
        }
    }

    public void salvar(Oriundo oriundo) throws SQLException {
        Connection con = DriverManager.getConnection(new conexao().url, new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
        PreparedStatement ps = null;
        String sqlSalvar = "INSERT INTO oriundo (descricao, data_cadastro, dia_fechamento, dia_pag ) VALUES (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sqlSalvar);
            ps.setString(1, oriundo.getDescricao());
            ps.setDate(2, oriundo.getData_cadastro());
            ps.setInt(3, oriundo.getDia_fechamento());
            ps.setInt(4, oriundo.getDia_pag());
            ps.executeUpdate();
           // JOptionPane.showMessageDialog(null, "Inserido Com Sucesso: ", "Mensagem do Sistema - Salvar", 1);
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

    public void atualizar(Oriundo oriundo) throws SQLException {
        Connection con = DriverManager.getConnection(new conexao().url, new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
        PreparedStatement ps = null;
        String sqlAtualizar = "UPDATE oriundo SET descricao=?, data_cadastro=?, dia_fechamento=?, dia_pag=? WHERE codigo=?";
        try {
            ps = con.prepareStatement(sqlAtualizar);
            ps.setString(1, oriundo.getDescricao());
            ps.setDate(2, oriundo.getData_cadastro());
            ps.setInt(3, oriundo.getDia_fechamento());
            ps.setInt(4, oriundo.getDia_pag());
            ps.setInt(5, oriundo.getCodigo());
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

    public void excluir(Oriundo oriundo) throws SQLException {
        Connection con = DriverManager.getConnection(new conexao().url, new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
        PreparedStatement ps = null;
        String sqlExcluir = "DELETE FROM oriundo WHERE codigo=?";
        try {
            ps = con.prepareStatement(sqlExcluir);
            ps.setInt(1, oriundo.getCodigo());
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

    public Oriundo localizar(Integer codigo) throws SQLException {
        Connection con = DriverManager.getConnection(new conexao().url, new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlLocalizar = "SELECT * FROM oriundo WHERE codigo=?";
        Oriundo oriundo = new Oriundo();
        try {
            ps = con.prepareStatement(sqlLocalizar);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            //if(!rs.next()){
            //return null;
            //}
            oriundo.setCodigo(rs.getInt("codigo"));
            oriundo.setDescricao(rs.getString("descricao"));
            oriundo.setData_cadastro(rs.getDate("data_cadastro"));
            oriundo.setDia_fechamento(rs.getInt("dia_fechamento"));
            oriundo.setDia_pag(rs.getInt("dia_pag"));
            return oriundo;
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
        return oriundo;
    }

    public List<Oriundo> listar() throws SQLException {
        List<Oriundo> resultado = new ArrayList<Oriundo>();
        Connection con = DriverManager.getConnection(new conexao().url, new conexao().config.getString("usuario"), new conexao().config.getString("senha"));
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlListar = "SELECT * FROM oriundo Order by codigo DESC";
        Oriundo oriundo;
        try {
            ps = con.prepareStatement(sqlListar);
            rs = ps.executeQuery();
            //if(rs==null){
            //return null;
            //}
            while (rs.next()) {
                oriundo = new Oriundo();
                oriundo.setCodigo(rs.getInt("codigo"));
                oriundo.setDescricao(rs.getString("descricao"));
                oriundo.setData_cadastro(rs.getDate("data_cadastro"));
                oriundo.setDia_fechamento(rs.getInt("dia_fechamento"));
                oriundo.setDia_pag(rs.getInt("dia_pag"));
                resultado.add(oriundo);
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