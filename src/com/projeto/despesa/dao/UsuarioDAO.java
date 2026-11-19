package com.projeto.despesa.dao;

import com.projeto.despesa.dto.Usuario;
import com.projeto.despesa.utilitarios.Mensagens;
import com.projeto.despesa.utilitarios.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Acesso a tabela {@code usuario} do banco de despesas. */
public class UsuarioDAO {

    public void salvar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nome, usuario, senha, nivel) VALUES (?, ?, ?, ?)";
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getUsuario());
            ps.setString(3, usuario.getSenha());
            ps.setInt(4, usuario.getNivel());
            ps.executeUpdate();
        }
        Mensagens.info("Mensagem do Sistema - Salvar", "Inserido Com Sucesso");
    }

    public void atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET nome=?, usuario=?, senha=?, nivel=? WHERE codigo=?";
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getUsuario());
            ps.setString(3, usuario.getSenha());
            ps.setInt(4, usuario.getNivel());
            ps.setInt(5, usuario.getCodigo());
            ps.executeUpdate();
        }
        Mensagens.info("Mensagem do Sistema - Atualizar", "Atualizado Com Sucesso");
    }

    public void excluir(Usuario usuario) throws SQLException {
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement("DELETE FROM usuario WHERE codigo=?")) {
            ps.setInt(1, usuario.getCodigo());
            ps.executeUpdate();
        }
        Mensagens.info("Mensagem do Sistema - Excluir", "Excluido Com Sucesso");
    }

    /** @return o usuario, ou {@code null} se o codigo nao existir */
    public Usuario localizar(Integer codigo) throws SQLException {
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM usuario WHERE codigo=?")) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    public List<Usuario> listar() throws SQLException {
        List<Usuario> resultado = new ArrayList<Usuario>();
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM usuario");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.add(mapear(rs));
            }
        }
        return resultado;
    }

    public static Usuario mapear(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setCodigo(rs.getInt("codigo"));
        usuario.setNome(rs.getString("nome"));
        usuario.setUsuario(rs.getString("usuario"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setNivel(rs.getInt("nivel"));
        return usuario;
    }
}
