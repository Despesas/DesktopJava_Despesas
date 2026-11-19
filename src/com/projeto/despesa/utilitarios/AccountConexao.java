package com.projeto.despesa.utilitarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Conexao com o banco de contas (tabela {@code usuario_server}), usado no login.
 * Le {@code account.url}, {@code account.usuario} e {@code account.senha} do
 * {@code config.properties} (antes ficavam em {@code account.properties} dentro do jar).
 *
 * @author WILL
 */
public class AccountConexao {

    private static final Logger LOG = Logger.getLogger(AccountConexao.class.getName());

    private final String url;
    private final String driver;
    private final String usuario;
    private final String senha;
    private Connection con;

    public AccountConexao() {
        this(ConfiguracaoBanco.carregar());
    }

    public AccountConexao(Properties config) {
        this.driver = config.getProperty("db.driver", ConfiguracaoBanco.DRIVER_PADRAO).trim();
        this.url = ConfiguracaoBanco.montarUrl(ConfiguracaoBanco.obrigatoria(config, "account.url"), null);
        this.usuario = ConfiguracaoBanco.obrigatoria(config, "account.usuario");
        this.senha = config.getProperty("account.senha", "");
    }

    public String getUrl() {
        return url;
    }

    /** Abre uma nova conexao; quem chama deve fecha-la (try-with-resources). */
    public Connection abrir() throws SQLException {
        conexao.carregarDriver(driver);
        return DriverManager.getConnection(url, usuario, senha);
    }

    public Connection conectar(String url, String driver, String usuario, String senha) {
        try {
            conexao.carregarDriver(driver);
            con = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Nao foi possivel conectar ao banco de contas", ex);
        }
        return con;
    }

    public void desconectar() {
        if (con == null) {
            return;
        }
        try {
            con.close();
        } catch (SQLException ex) {
            LOG.log(Level.WARNING, "Nao foi possivel desconectar", ex);
        }
    }
}
