package com.projeto.despesa.dao;

import com.projeto.despesa.dto.Oriundo;
import com.projeto.despesa.utilitarios.Mensagens;
import com.projeto.despesa.utilitarios.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Acesso a tabela {@code oriundo} (origens de pagamento: cartoes, contas...). */
public class OriundoDAO {

    public void salvar(Oriundo oriundo) throws SQLException {
        String sql = "INSERT INTO oriundo (descricao, data_cadastro, dia_fechamento, dia_pag) VALUES (?, ?, ?, ?)";
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, oriundo.getDescricao());
            ps.setDate(2, oriundo.getData_cadastro());
            ps.setInt(3, oriundo.getDia_fechamento());
            ps.setInt(4, oriundo.getDia_pag());
            ps.executeUpdate();
        }
    }

    public void atualizar(Oriundo oriundo) throws SQLException {
        String sql = "UPDATE oriundo SET descricao=?, data_cadastro=?, dia_fechamento=?, dia_pag=? WHERE codigo=?";
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, oriundo.getDescricao());
            ps.setDate(2, oriundo.getData_cadastro());
            ps.setInt(3, oriundo.getDia_fechamento());
            ps.setInt(4, oriundo.getDia_pag());
            ps.setInt(5, oriundo.getCodigo());
            ps.executeUpdate();
        }
        Mensagens.info("Mensagem do Sistema - Atualizar", "Atualizado Com Sucesso");
    }

    public void excluir(Oriundo oriundo) throws SQLException {
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement("DELETE FROM oriundo WHERE codigo=?")) {
            ps.setInt(1, oriundo.getCodigo());
            ps.executeUpdate();
        }
        Mensagens.info("Mensagem do Sistema - Excluir", "Excluido Com Sucesso");
    }

    /** @return a origem, ou {@code null} se o codigo nao existir */
    public Oriundo localizar(Integer codigo) throws SQLException {
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM oriundo WHERE codigo=?")) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    public List<Oriundo> listar() throws SQLException {
        List<Oriundo> resultado = new ArrayList<Oriundo>();
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM oriundo ORDER BY codigo DESC");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.add(mapear(rs));
            }
        }
        return resultado;
    }

    public static Oriundo mapear(ResultSet rs) throws SQLException {
        Oriundo oriundo = new Oriundo();
        oriundo.setCodigo(rs.getInt("codigo"));
        oriundo.setDescricao(rs.getString("descricao"));
        oriundo.setData_cadastro(rs.getDate("data_cadastro"));
        oriundo.setDia_fechamento(rs.getInt("dia_fechamento"));
        oriundo.setDia_pag(rs.getInt("dia_pag"));
        return oriundo;
    }
}
