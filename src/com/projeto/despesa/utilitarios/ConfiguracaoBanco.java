package com.projeto.despesa.utilitarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Le as configuracoes de banco de dados de um arquivo {@code config.properties}
 * externo ao jar (nenhuma credencial fica no codigo-fonte).
 *
 * <p>Ordem de busca do arquivo:</p>
 * <ol>
 * <li>caminho informado na propriedade de sistema {@code despesas.config};</li>
 * <li>{@code config.properties} no diretorio de trabalho;</li>
 * <li>{@code ~/.despesas/config.properties}.</li>
 * </ol>
 *
 * <p>Veja {@code config.example.properties} na raiz do projeto.</p>
 */
public final class ConfiguracaoBanco {

    public static final String PROP_ARQUIVO = "despesas.config";
    public static final String DRIVER_PADRAO = "com.mysql.jdbc.Driver";

    private ConfiguracaoBanco() {
    }

    /** Diretorio de configuracao do usuario ({@code ~/.despesas}). */
    public static File diretorioUsuario() {
        return new File(System.getProperty("user.home"), ".despesas");
    }

    /** Localiza o arquivo de configuracao, ou {@code null} se nao existir. */
    public static File localizarArquivo() {
        String informado = System.getProperty(PROP_ARQUIVO);
        if (informado != null && informado.trim().length() > 0) {
            return new File(informado.trim());
        }
        File local = new File("config.properties");
        if (local.isFile()) {
            return local;
        }
        File usuario = new File(diretorioUsuario(), "config.properties");
        if (usuario.isFile()) {
            return usuario;
        }
        return null;
    }

    /** Carrega o {@code config.properties}; devolve propriedades vazias se nao houver arquivo. */
    public static Properties carregar() {
        File arquivo = localizarArquivo();
        if (arquivo == null) {
            return new Properties();
        }
        return carregar(arquivo);
    }

    public static Properties carregar(File arquivo) {
        Properties p = new Properties();
        if (arquivo == null || !arquivo.isFile()) {
            return p;
        }
        try (InputStream in = new FileInputStream(arquivo)) {
            p.load(in);
        } catch (IOException ex) {
            throw new IllegalStateException("Nao foi possivel ler " + arquivo.getAbsolutePath(), ex);
        }
        return p;
    }

    /**
     * Arquivo onde o login grava a conexao do banco de despesas do usuario
     * (chave {@code sessao.arquivo}; padrao {@code ~/.despesas/bancodedados.properties}).
     */
    public static File arquivoSessao(Properties config) {
        String caminho = config.getProperty("sessao.arquivo");
        if (caminho != null && caminho.trim().length() > 0) {
            return new File(caminho.trim());
        }
        return new File(diretorioUsuario(), "bancodedados.properties");
    }

    /**
     * Monta a URL JDBC do MySQL. Aceita tanto uma URL completa
     * ({@code jdbc:mysql://host:3306/banco}) quanto apenas o host, com a porta
     * opcionalmente seguida do banco ({@code "3306/despesas"}), formato usado
     * pela tabela {@code usuario_server}.
     */
    public static String montarUrl(String host, String porta) {
        if (host == null || host.trim().length() == 0) {
            throw new IllegalArgumentException("Host do banco nao informado");
        }
        String h = host.trim();
        if (h.startsWith("jdbc:")) {
            return h;
        }
        if (h.startsWith("//")) {
            h = h.substring(2);
        }
        while (h.endsWith("/") || h.endsWith(".")) {
            h = h.substring(0, h.length() - 1);
        }
        StringBuilder url = new StringBuilder("jdbc:mysql://").append(h);
        if (porta != null && porta.trim().length() > 0) {
            String p = porta.trim();
            url.append(p.startsWith("/") ? "" : ":").append(p);
        }
        return url.toString();
    }

    /** Le uma chave obrigatoria, com mensagem clara quando ausente. */
    public static String obrigatoria(Properties p, String chave) {
        String v = p.getProperty(chave);
        if (v == null) {
            throw new IllegalStateException("Configuracao '" + chave
                    + "' ausente. Copie config.example.properties para config.properties e ajuste.");
        }
        return v.trim();
    }
}
