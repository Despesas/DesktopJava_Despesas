package com.projeto.despesa.dao;

import com.projeto.despesa.dto.Despesas;
import com.projeto.despesa.utilitarios.Data;
import com.projeto.despesa.utilitarios.Mensagens;
import com.projeto.despesa.utilitarios.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Acesso a tabela {@code despesas}. Conexoes, statements e result sets sao
 * sempre fechados (try-with-resources) e todos os parametros vao por
 * PreparedStatement.
 */
public class DespesasDAO {

    private static final String COLUNAS = "descricao, data_desp, data_pag, parcelas, parcela, valor_parcela, valor, status, obs, cod_orindo, cod_usuario";
    private static final String SQL_INSERIR = "INSERT INTO despesas (" + COLUNAS + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public void salvar(Despesas despesas) throws SQLException {
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement(SQL_INSERIR)) {
            setPStatment(ps, despesas);
            ps.executeUpdate();
        }
        Mensagens.info("Mensagem do Sistema - Salvar", "Inserido Com Sucesso");
    }

    /**
     * Grava a parcela atual e as seguintes, uma por mes, acrescentando
     * " P.:n" na descricao de cada uma. Tudo numa unica transacao.
     */
    public void salvarParcelado(Despesas despesas) throws SQLException {
        List<Despesas> parcelas = gerarParcelas(despesas);
        try (Connection con = conexao.novaConexao()) {
            boolean autoCommit = con.getAutoCommit();
            con.setAutoCommit(false);
            try (PreparedStatement ps = con.prepareStatement(SQL_INSERIR)) {
                for (Despesas parcela : parcelas) {
                    setPStatment(ps, parcela);
                    ps.executeUpdate();
                }
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(autoCommit);
            }
        }
        Mensagens.info("Mensagem do Sistema - Salvar", "Inserido Parcelas Com Sucesso");
    }

    /**
     * Gera as despesas da parcela atual ate a ultima, com a data de pagamento
     * avancando um mes por parcela. Nao altera o objeto recebido.
     */
    public static List<Despesas> gerarParcelas(Despesas base) {
        List<Despesas> resultado = new ArrayList<Despesas>();
        Data data = new Data();
        java.sql.Date dataPag = base.getData_pag();
        for (int p = base.getParcela(); p <= base.getParcelas(); p++) {
            Despesas d = copiar(base);
            d.setParcela(p);
            d.setDescricao(base.getDescricao() + " P.:" + p);
            d.setData_pag(dataPag);
            resultado.add(d);
            if (dataPag != null) {
                dataPag = data.getDataBanco(data.getProximoMes(dataPag));
            }
        }
        return resultado;
    }

    private static Despesas copiar(Despesas o) {
        Despesas d = new Despesas();
        d.setCodigo(o.getCodigo());
        d.setDescricao(o.getDescricao());
        d.setData_desp(o.getData_desp());
        d.setData_pag(o.getData_pag());
        d.setParcelas(o.getParcelas());
        d.setParcela(o.getParcela());
        d.setValor_parcela(o.getValor_parcela());
        d.setValor(o.getValor());
        d.setStatus(o.getStatus());
        d.setObs(o.getObs());
        d.setCod_orindo(o.getCod_orindo());
        d.setCod_usuario(o.getCod_usuario());
        return d;
    }

    static void setPStatment(PreparedStatement ps, Despesas despesas) throws SQLException {
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
    }

    public void atualizar(Despesas despesas) throws SQLException {
        String sql = "UPDATE despesas SET descricao=?, data_desp=?, data_pag=?, parcelas=?, parcela=?, valor_parcela=?, valor=?, status=?, obs=?, cod_orindo=?, cod_usuario=? WHERE codigo=?";
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement(sql)) {
            setPStatment(ps, despesas);
            ps.setInt(12, despesas.getCodigo());
            ps.executeUpdate();
        }
        Mensagens.info("Mensagem do Sistema - Atualizar", "Atualizado Com Sucesso");
    }

    public void excluir(Despesas despesas) throws SQLException {
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement("DELETE FROM despesas WHERE codigo=?")) {
            ps.setInt(1, despesas.getCodigo());
            ps.executeUpdate();
        }
        Mensagens.info("Mensagem do Sistema - Excluir", "Excluido Com Sucesso");
    }

    /** @return a despesa, ou {@code null} se o codigo nao existir */
    public Despesas localizar(Integer codigo) throws SQLException {
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM despesas WHERE codigo=?")) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    public List<Despesas> listar() throws SQLException {
        return consultar("SELECT * FROM despesas ORDER BY descricao", new ArrayList<Object>());
    }

    /** Filtra por mes/ano da data de pagamento e por origem; 0 ignora o filtro. */
    public List<Despesas> listarByMes(int mes, int ano, int cod_o) throws SQLException {
        List<Object> parametros = new ArrayList<Object>();
        String sql = montarFiltroMes(mes, ano, cod_o, parametros);
        List<Despesas> resultado = consultar(sql, parametros);
        for (Despesas d : resultado) {
            d.setSql(sql);
        }
        return resultado;
    }

    /** Monta o SELECT com os filtros informados, adicionando os valores em {@code parametros}. */
    public static String montarFiltroMes(int mes, int ano, int cod_o, List<Object> parametros) {
        StringBuilder sql = new StringBuilder("SELECT * FROM despesas");
        List<String> condicoes = new ArrayList<String>();
        if (mes > 0) {
            condicoes.add("MONTH(data_pag) = ?");
            parametros.add(mes);
        }
        if (ano > 0) {
            condicoes.add("YEAR(data_pag) = ?");
            parametros.add(ano);
        }
        if (cod_o > 0) {
            condicoes.add("cod_orindo = ?");
            parametros.add(cod_o);
        }
        if (!condicoes.isEmpty()) {
            sql.append(" WHERE ");
            for (int i = 0; i < condicoes.size(); i++) {
                sql.append(i == 0 ? "" : " AND ").append(condicoes.get(i));
            }
            sql.append(" ORDER BY data_desp DESC, descricao ASC");
        }
        return sql.toString();
    }

    public List<String> listAnoByDespesas() throws SQLException {
        List<String> resultado = new ArrayList<String>();
        String sql = "SELECT YEAR(data_pag) AS Ano FROM despesas GROUP BY YEAR(data_pag)";
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.add(rs.getString("Ano"));
            }
        }
        return resultado;
    }

    private List<Despesas> consultar(String sql, List<Object> parametros) throws SQLException {
        List<Despesas> resultado = new ArrayList<Despesas>();
        try (Connection con = conexao.novaConexao();
                PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultado.add(mapear(rs));
                }
            }
        }
        return resultado;
    }

    /** Converte a linha atual do ResultSet em {@link Despesas}. */
    public static Despesas mapear(ResultSet rs) throws SQLException {
        Despesas despesas = new Despesas();
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
        return despesas;
    }
}
