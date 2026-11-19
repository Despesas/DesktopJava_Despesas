package com.projeto.despesa.dao;

import com.projeto.despesa.dto.Despesas_pagamento;
import com.projeto.despesa.utilitarios.Mensagens;
import com.projeto.despesa.utilitarios.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Acesso a tabela {@code despesas_pagamento} (pagamento de uma despesa). */
public class Despesas_pagamentoDAO {

    public void salvar(Despesas_pagamento despesas_pagamento) throws SQLException {
        // a chave primaria e (forma_pagamento_codigo, despesas_codigo); antes a forma nao era gravada
        String sql = "INSERT INTO despesas_pagamento (forma_pagamento_codigo, despesas_codigo, data_pagamento) VALUES (?, ?, ?)";
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, despesas_pagamento.getForma_pagamento_codigo());
            ps.setInt(2, despesas_pagamento.getDespesas_codigo());
            ps.setTimestamp(3, despesas_pagamento.getData_pagamento());
            ps.executeUpdate();
        }
        Mensagens.info("Mensagem do Sistema - Salvar", "Inserido Com Sucesso");
    }

    public void atualizar(Despesas_pagamento despesas_pagamento) throws SQLException {
        String sql = "UPDATE despesas_pagamento SET data_pagamento=? WHERE forma_pagamento_codigo=? AND despesas_codigo=?";
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, despesas_pagamento.getData_pagamento());
            ps.setInt(2, despesas_pagamento.getForma_pagamento_codigo());
            ps.setInt(3, despesas_pagamento.getDespesas_codigo());
            ps.executeUpdate();
        }
        Mensagens.info("Mensagem do Sistema - Atualizar", "Atualizado Com Sucesso");
    }

    public void excluir(Despesas_pagamento despesas_pagamento) throws SQLException {
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement("DELETE FROM despesas_pagamento WHERE forma_pagamento_codigo=? AND despesas_codigo=?")) {
            ps.setInt(1, despesas_pagamento.getForma_pagamento_codigo());
            ps.setInt(2, despesas_pagamento.getDespesas_codigo());
            ps.executeUpdate();
        }
        Mensagens.info("Mensagem do Sistema - Excluir", "Excluido Com Sucesso");
    }

    /** @return o registro, ou {@code null} se nao existir */
    public Despesas_pagamento localizar(Integer forma_pagamento_codigo) throws SQLException {
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM despesas_pagamento WHERE forma_pagamento_codigo=?")) {
            ps.setInt(1, forma_pagamento_codigo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    public List<Despesas_pagamento> listar() throws SQLException {
        List<Despesas_pagamento> resultado = new ArrayList<Despesas_pagamento>();
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM despesas_pagamento");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.add(mapear(rs));
            }
        }
        return resultado;
    }

    public static Despesas_pagamento mapear(ResultSet rs) throws SQLException {
        Despesas_pagamento d = new Despesas_pagamento();
        d.setForma_pagamento_codigo(rs.getInt("forma_pagamento_codigo"));
        d.setDespesas_codigo(rs.getInt("despesas_codigo"));
        d.setData_pagamento(rs.getTimestamp("data_pagamento"));
        return d;
    }
}
