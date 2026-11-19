package com.projeto.despesa.utilitarios;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Conexao com o banco de despesas.
 *
 * <p>Se o {@code config.properties} tiver {@code db.url}, usa diretamente
 * {@code db.url}, {@code db.usuario} e {@code db.senha}. Caso contrario, usa o
 * arquivo de sessao gravado pelo login (chaves {@code url}, {@code porta.banco},
 * {@code usuario}, {@code senha}), que antes ficava fixo em
 * {@code c://Despesas/lib/bancodedados.properties}.</p>
 *
 * @author WILL
 */
public class conexao {

    private static final Logger LOG = Logger.getLogger(conexao.class.getName());

    public String url;
    public String driver;
    public String usuario;
    public String senha;
    private Connection con;

    public conexao() {
        this(ConfiguracaoBanco.carregar());
    }

    public conexao(Properties config) {
        lerPropriedades(config);
    }

    private void lerPropriedades(Properties config) {
        this.driver = config.getProperty("db.driver", ConfiguracaoBanco.DRIVER_PADRAO).trim();
        if (config.getProperty("db.url") != null) {
            this.url = ConfiguracaoBanco.montarUrl(config.getProperty("db.url"), null);
            this.usuario = ConfiguracaoBanco.obrigatoria(config, "db.usuario");
            this.senha = config.getProperty("db.senha", "");
            return;
        }
        File sessao = ConfiguracaoBanco.arquivoSessao(config);
        if (!sessao.isFile()) {
            throw new IllegalStateException("Conexao do banco de despesas nao configurada: defina db.url no "
                    + "config.properties ou faca login para gerar " + sessao.getAbsolutePath());
        }
        Properties s = ConfiguracaoBanco.carregar(sessao);
        this.driver = s.getProperty("driver", this.driver).trim();
        this.url = ConfiguracaoBanco.montarUrl(ConfiguracaoBanco.obrigatoria(s, "url"), s.getProperty("porta.banco"));
        this.usuario = ConfiguracaoBanco.obrigatoria(s, "usuario");
        this.senha = s.getProperty("senha", "");
    }

    /** Abre uma nova conexao; quem chama deve fecha-la (try-with-resources). */
    public Connection abrir() throws SQLException {
        carregarDriver(driver);
        return DriverManager.getConnection(url, usuario, senha);
    }

    /** Atalho usado pelos DAOs: le a configuracao e abre uma conexao. */
    public static Connection novaConexao() throws SQLException {
        return new conexao().abrir();
    }

    static void carregarDriver(String driver) throws SQLException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            throw new SQLException("Driver JDBC nao encontrado: " + driver, ex);
        }
    }

    public Connection conectar(String url, String driver, String usuario, String senha) {
        try {
            carregarDriver(driver);
            con = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Nao foi possivel conectar ao banco", ex);
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
