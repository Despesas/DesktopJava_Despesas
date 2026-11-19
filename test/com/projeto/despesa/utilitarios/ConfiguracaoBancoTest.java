package com.projeto.despesa.utilitarios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ConfiguracaoBancoTest {

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();

    @Test
    public void montarUrlComHostEPortaComBanco() {
        assertEquals("jdbc:mysql://localhost:3306/despesas", ConfiguracaoBanco.montarUrl("localhost", "3306/despesas"));
    }

    @Test
    public void montarUrlAceitaUrlCompleta() {
        assertEquals("jdbc:mysql://localhost:3306/despesas",
                ConfiguracaoBanco.montarUrl(" jdbc:mysql://localhost:3306/despesas ", "9999"));
    }

    @Test
    public void montarUrlLimpaBarrasEPontoFinal() {
        assertEquals("jdbc:mysql://db.exemplo:3306", ConfiguracaoBanco.montarUrl("//db.exemplo.", "3306"));
        assertEquals("jdbc:mysql://localhost/despesas", ConfiguracaoBanco.montarUrl("localhost", "/despesas"));
        assertEquals("jdbc:mysql://localhost", ConfiguracaoBanco.montarUrl("localhost", null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void montarUrlSemHostFalha() {
        ConfiguracaoBanco.montarUrl(" ", "3306");
    }

    @Test
    public void carregarArquivo() throws IOException {
        File f = gravar(tmp.newFile("config.properties"), "db.url", "jdbc:mysql://localhost:3306/despesas", "db.usuario", "usuario");
        Properties p = ConfiguracaoBanco.carregar(f);
        assertEquals("usuario", p.getProperty("db.usuario"));
    }

    @Test
    public void carregarArquivoInexistenteDevolveVazio() {
        assertTrue(ConfiguracaoBanco.carregar(new File(tmp.getRoot(), "nao-existe.properties")).isEmpty());
    }

    @Test
    public void localizarArquivoPelaPropriedadeDeSistema() throws IOException {
        File f = tmp.newFile("outro.properties");
        String anterior = System.getProperty(ConfiguracaoBanco.PROP_ARQUIVO);
        try {
            System.setProperty(ConfiguracaoBanco.PROP_ARQUIVO, f.getAbsolutePath());
            assertEquals(f.getAbsolutePath(), ConfiguracaoBanco.localizarArquivo().getAbsolutePath());
        } finally {
            if (anterior == null) {
                System.clearProperty(ConfiguracaoBanco.PROP_ARQUIVO);
            } else {
                System.setProperty(ConfiguracaoBanco.PROP_ARQUIVO, anterior);
            }
        }
    }

    @Test
    public void arquivoSessaoPadraoFicaNaHomeDoUsuario() {
        File padrao = ConfiguracaoBanco.arquivoSessao(new Properties());
        assertEquals("bancodedados.properties", padrao.getName());
        assertEquals(".despesas", padrao.getParentFile().getName());

        Properties p = new Properties();
        p.setProperty("sessao.arquivo", "/tmp/sessao.properties");
        assertEquals(new File("/tmp/sessao.properties"), ConfiguracaoBanco.arquivoSessao(p));
    }

    @Test
    public void obrigatoriaAusenteTemMensagemClara() {
        try {
            ConfiguracaoBanco.obrigatoria(new Properties(), "account.url");
            fail();
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("account.url"));
            assertTrue(e.getMessage().contains("config.example.properties"));
        }
    }

    @Test
    public void conexaoModoDireto() {
        Properties p = new Properties();
        p.setProperty("db.url", "jdbc:mysql://localhost:3306/despesas");
        p.setProperty("db.usuario", "usuario");
        p.setProperty("db.senha", "senha");
        conexao c = new conexao(p);
        assertEquals("jdbc:mysql://localhost:3306/despesas", c.url);
        assertEquals("usuario", c.usuario);
        assertEquals("senha", c.senha);
        assertEquals(ConfiguracaoBanco.DRIVER_PADRAO, c.driver);
    }

    @Test
    public void conexaoModoSessaoGravadaPeloLogin() throws IOException {
        File sessao = gravar(tmp.newFile("sessao.properties"),
                "url", "localhost", "porta.banco", "3306/despesas", "usuario", "usuario", "senha", "senha");
        Properties p = new Properties();
        p.setProperty("sessao.arquivo", sessao.getAbsolutePath());
        conexao c = new conexao(p);
        assertEquals("jdbc:mysql://localhost:3306/despesas", c.url);
        assertEquals("usuario", c.usuario);
    }

    @Test(expected = IllegalStateException.class)
    public void conexaoSemConfiguracaoFalhaComMensagem() {
        Properties p = new Properties();
        p.setProperty("sessao.arquivo", new File(tmp.getRoot(), "ausente.properties").getAbsolutePath());
        new conexao(p);
    }

    @Test
    public void accountConexaoLeChavesAccount() {
        Properties p = new Properties();
        p.setProperty("account.url", "jdbc:mysql://localhost:3306/despesas_contas");
        p.setProperty("account.usuario", "usuario");
        assertEquals("jdbc:mysql://localhost:3306/despesas_contas", new AccountConexao(p).getUrl());
    }

    @Test(expected = IllegalStateException.class)
    public void accountConexaoSemUrlFalha() {
        new AccountConexao(new Properties());
    }

    @Test
    public void exemploDoRepositorioNaoTemCredenciaisReais() {
        File exemplo = new File("config.example.properties");
        assertTrue("config.example.properties deve existir na raiz", exemplo.isFile());
        Properties p = ConfiguracaoBanco.carregar(exemplo);
        assertTrue(p.getProperty("db.url").contains("localhost"));
        assertTrue(p.getProperty("account.url").contains("localhost"));
        assertEquals("usuario", p.getProperty("db.usuario"));
        assertEquals("senha", p.getProperty("db.senha"));
    }

    private static File gravar(File f, String... chavesValores) throws IOException {
        Properties p = new Properties();
        for (int i = 0; i < chavesValores.length; i += 2) {
            p.setProperty(chavesValores[i], chavesValores[i + 1]);
        }
        try (OutputStream out = new FileOutputStream(f)) {
            p.store(out, null);
        }
        return f;
    }
}
