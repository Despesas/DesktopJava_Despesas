package com.projeto.despesa.dao;

import com.projeto.despesa.dto.Forma_pagamento;
import com.projeto.despesa.utilitarios.Mensagens;
import com.projeto.despesa.utilitarios.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Acesso a tabela {@code forma_pagamento}. */
public class Forma_pagamentoDAO {

    public void salvar(Forma_pagamento forma_pagamento) throws SQLException {
        String sql = "INSERT INTO forma_pagamento (nome, data_cadastro, data_alteracao) VALUES (?, ?, ?)";
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, forma_pagamento.getNome());
            ps.setDate(2, forma_pagamento.getData_cadastro());
            ps.setTimestamp(3, forma_pagamento.getData_alteracao());
            ps.executeUpdate();
        }
        Mensagens.info("Mensagem do Sistema - Salvar", "Inserido Com Sucesso");
    }

    public void atualizar(Forma_pagamento forma_pagamento) throws SQLException {
        String sql = "UPDATE forma_pagamento SET nome=?, data_cadastro=?, data_alteracao=? WHERE codigo=?";
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, forma_pagamento.getNome());
            ps.setDate(2, forma_pagamento.getData_cadastro());
            ps.setTimestamp(3, forma_pagamento.getData_alteracao());
            ps.setInt(4, forma_pagamento.getCodigo());
            ps.executeUpdate();
        }
        Mensagens.info("Mensagem do Sistema - Atualizar", "Atualizado Com Sucesso");
    }

    public void excluir(Forma_pagamento forma_pagamento) throws SQLException {
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement("DELETE FROM forma_pagamento WHERE codigo=?")) {
            ps.setInt(1, forma_pagamento.getCodigo());
            ps.executeUpdate();
        }
        Mensagens.info("Mensagem do Sistema - Excluir", "Excluido Com Sucesso");
    }

    /** @return a forma de pagamento, ou {@code null} se o codigo nao existir */
    public Forma_pagamento localizar(Integer codigo) throws SQLException {
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM forma_pagamento WHERE codigo=?")) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    public List<Forma_pagamento> listar() throws SQLException {
        List<Forma_pagamento> resultado = new ArrayList<Forma_pagamento>();
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM forma_pagamento");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.add(mapear(rs));
            }
        }
        return resultado;
    }

    public static Forma_pagamento mapear(ResultSet rs) throws SQLException {
        Forma_pagamento forma_pagamento = new Forma_pagamento();
        forma_pagamento.setCodigo(rs.getInt("codigo"));
        forma_pagamento.setNome(rs.getString("nome"));
        forma_pagamento.setData_cadastro(rs.getDate("data_cadastro"));
        forma_pagamento.setData_alteracao(rs.getTimestamp("data_alteracao"));
        return forma_pagamento;
    }
}
