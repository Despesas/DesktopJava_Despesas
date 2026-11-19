package com.projeto.despesa.dao;

import com.projeto.despesa.dto.Usuario_server;
import com.projeto.despesa.utilitarios.AccountConexao;
import com.projeto.despesa.utilitarios.Mensagens;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Acesso a tabela {@code usuario_server} do banco de contas: cada registro
 * guarda o login do sistema e a conexao do banco de despesas daquele usuario.
 */
public class Usuario_serverDAO {

    public void salvar(Usuario_server u) throws SQLException {
        String sql = "INSERT INTO usuario_server (nome, user_sys, senha_sys, user_server, senha_server, porta_server, url_server) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = new AccountConexao().abrir();
                PreparedStatement ps = con.prepareStatement(sql)) {
            preencher(ps, u);
            ps.executeUpdate();
        }
        Mensagens.info("Mensagem do Sistema - Salvar", "Inserido Com Sucesso");
    }

    public void atualizar(Usuario_server u) throws SQLException {
        String sql = "UPDATE usuario_server SET nome=?, user_sys=?, senha_sys=?, user_server=?, senha_server=?, porta_server=?, url_server=? WHERE codigo=?";
        try (Connection con = new AccountConexao().abrir();
                PreparedStatement ps = con.prepareStatement(sql)) {
            preencher(ps, u);
            ps.setInt(8, u.getCodigo());
            ps.executeUpdate();
        }
        Mensagens.info("Mensagem do Sistema - Atualizar", "Atualizado Com Sucesso");
    }

    private static void preencher(PreparedStatement ps, Usuario_server u) throws SQLException {
        ps.setString(1, u.getNome());
        ps.setString(2, u.getUser_sys());
        ps.setString(3, u.getSenha_sys());
        ps.setString(4, u.getUser_server());
        ps.setString(5, u.getSenha_server());
        ps.setString(6, u.getPorta_server());
        ps.setString(7, u.getUrl_server());
    }

    public void excluir(Usuario_server u) throws SQLException {
        try (Connection con = new AccountConexao().abrir();
                PreparedStatement ps = con.prepareStatement("DELETE FROM usuario_server WHERE codigo=?")) {
            ps.setInt(1, u.getCodigo());
            ps.executeUpdate();
        }
        Mensagens.info("Mensagem do Sistema - Excluir", "Excluido Com Sucesso");
    }

    /** @return o usuario autenticado, ou {@code null} se usuario/senha nao conferem */
    public Usuario_server Login(String usuario, String senha) throws SQLException {
        String sql = "SELECT * FROM usuario_server WHERE user_sys=? AND senha_sys=?";
        try (Connection con = new AccountConexao().abrir();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setString(2, senha);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    /** @return o registro, ou {@code null} se o codigo nao existir */
    public Usuario_server localizar(Integer codigo) throws SQLException {
        try (Connection con = new AccountConexao().abrir();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM usuario_server WHERE codigo=?")) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    public List<Usuario_server> listar() throws SQLException {
        List<Usuario_server> resultado = new ArrayList<Usuario_server>();
        try (Connection con = new AccountConexao().abrir();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM usuario_server");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.add(mapear(rs));
            }
        }
        return resultado;
    }

    public static Usuario_server mapear(ResultSet rs) throws SQLException {
        Usuario_server u = new Usuario_server();
        u.setCodigo(rs.getInt("codigo"));
        u.setNome(rs.getString("nome"));
        u.setUser_sys(rs.getString("user_sys"));
        u.setSenha_sys(rs.getString("senha_sys"));
        u.setUser_server(rs.getString("user_server"));
        u.setSenha_server(rs.getString("senha_server"));
        u.setPorta_server(rs.getString("porta_server"));
        u.setUrl_server(rs.getString("url_server"));
        return u;
    }
}
