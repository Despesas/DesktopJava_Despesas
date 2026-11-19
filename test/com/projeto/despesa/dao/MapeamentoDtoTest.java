package com.projeto.despesa.dao;

import static org.junit.Assert.assertEquals;

import com.projeto.despesa.dto.Despesas;
import com.projeto.despesa.dto.Despesas_pagamento;
import com.projeto.despesa.dto.Forma_pagamento;
import com.projeto.despesa.dto.Oriundo;
import com.projeto.despesa.dto.Usuario;
import com.projeto.despesa.dto.Usuario_server;
import com.projeto.despesa.event.EventLogin;
import com.projeto.despesa.utilitarios.ConfiguracaoBanco;
import com.projeto.despesa.utilitarios.conexao;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Proxy;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/** Mapeamento ResultSet -> DTO dos DAOs, usando um ResultSet falso (sem MySQL). */
public class MapeamentoDtoTest {

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();

    @Test
    public void mapearDespesas() throws Exception {
        Map<String, Object> l = new HashMap<String, Object>();
        l.put("codigo", 10);
        l.put("descricao", "Mercado");
        l.put("data_desp", java.sql.Date.valueOf("2016-05-02"));
        l.put("data_pag", java.sql.Date.valueOf("2016-06-05"));
        l.put("parcelas", 3);
        l.put("parcela", 1);
        l.put("valor_parcela", 33.33f);
        l.put("valor", 99.99f);
        l.put("status", "Aberto");
        l.put("obs", "teste");
        l.put("cod_orindo", 200);
        l.put("cod_usuario", 1);

        Despesas d = DespesasDAO.mapear(linha(l));
        assertEquals(Integer.valueOf(10), d.getCodigo());
        assertEquals("Mercado", d.getDescricao());
        assertEquals("2016-05-02", d.getData_desp().toString());
        assertEquals("2016-06-05", d.getData_pag().toString());
        assertEquals(Integer.valueOf(3), d.getParcelas());
        assertEquals(Integer.valueOf(1), d.getParcela());
        assertEquals(33.33f, d.getValor_parcela(), 0.001f);
        assertEquals(99.99f, d.getValor(), 0.001f);
        assertEquals("Aberto", d.getStatus());
        assertEquals("teste", d.getObs());
        assertEquals(Integer.valueOf(200), d.getCod_orindo());
        assertEquals(Integer.valueOf(1), d.getCod_usuario());
    }

    @Test
    public void mapearOriundo() throws Exception {
        Map<String, Object> l = new HashMap<String, Object>();
        l.put("codigo", 300);
        l.put("descricao", "Cartao");
        l.put("data_cadastro", java.sql.Date.valueOf("2015-11-13"));
        l.put("dia_fechamento", 20);
        l.put("dia_pag", 5);
        Oriundo o = OriundoDAO.mapear(linha(l));
        assertEquals(Integer.valueOf(300), o.getCodigo());
        assertEquals("Cartao", o.getDescricao());
        assertEquals(Integer.valueOf(20), o.getDia_fechamento());
        assertEquals(Integer.valueOf(5), o.getDia_pag());
        // codigos acima de 127 agora sao comparados com equals nas telas
        assertEquals(Integer.valueOf(300), Integer.valueOf(o.getCodigo().intValue()));
    }

    @Test
    public void mapearFormaPagamento() throws Exception {
        Map<String, Object> l = new HashMap<String, Object>();
        Timestamp ts = Timestamp.valueOf("2016-05-28 15:15:28");
        l.put("codigo", 2);
        l.put("nome", "Debito");
        l.put("data_cadastro", java.sql.Date.valueOf("2016-05-28"));
        l.put("data_alteracao", ts);
        Forma_pagamento f = Forma_pagamentoDAO.mapear(linha(l));
        assertEquals("Debito", f.getNome());
        assertEquals(ts, f.getData_alteracao());
    }

    @Test
    public void mapearDespesasPagamento() throws Exception {
        Map<String, Object> l = new HashMap<String, Object>();
        l.put("forma_pagamento_codigo", 2);
        l.put("despesas_codigo", 10);
        l.put("data_pagamento", Timestamp.valueOf("2016-06-05 00:00:00"));
        Despesas_pagamento d = Despesas_pagamentoDAO.mapear(linha(l));
        assertEquals(Integer.valueOf(2), d.getForma_pagamento_codigo());
        assertEquals(Integer.valueOf(10), d.getDespesas_codigo());
    }

    @Test
    public void mapearUsuario() throws Exception {
        Map<String, Object> l = new HashMap<String, Object>();
        l.put("codigo", 1);
        l.put("nome", "Fulano");
        l.put("usuario", "fulano");
        l.put("senha", "senha");
        l.put("nivel", 2);
        Usuario u = UsuarioDAO.mapear(linha(l));
        assertEquals("fulano", u.getUsuario());
        assertEquals(Integer.valueOf(2), u.getNivel());
    }

    @Test
    public void usuarioServerViraArquivoDeSessaoLidoPelaConexao() throws Exception {
        Map<String, Object> l = new HashMap<String, Object>();
        l.put("codigo", 1);
        l.put("nome", "Fulano");
        l.put("user_sys", "fulano");
        l.put("senha_sys", "senha");
        l.put("user_server", "usuario");
        l.put("senha_server", "senha");
        l.put("porta_server", "3306/despesas");
        l.put("url_server", "localhost");
        Usuario_server u = Usuario_serverDAO.mapear(linha(l));
        assertEquals("localhost", u.getUrl_server());
        assertEquals("3306/despesas", u.getPorta_server());

        Properties sessao = EventLogin.propriedadesSessao(u);
        File arquivo = tmp.newFile("sessao.properties");
        try (OutputStream out = new FileOutputStream(arquivo)) {
            sessao.store(out, null);
        }
        Properties config = new Properties();
        config.setProperty("sessao.arquivo", arquivo.getAbsolutePath());
        conexao c = new conexao(config);
        assertEquals("jdbc:mysql://localhost:3306/despesas", c.url);
        assertEquals("usuario", c.usuario);
        assertEquals("senha", c.senha);
        assertEquals(ConfiguracaoBanco.DRIVER_PADRAO, c.driver);
    }

    /** ResultSet falso que responde getInt/getFloat/getString/getDate/getTimestamp por nome de coluna. */
    static ResultSet linha(final Map<String, Object> valores) {
        return (ResultSet) Proxy.newProxyInstance(ResultSet.class.getClassLoader(), new Class<?>[]{ResultSet.class},
                (proxy, metodo, args) -> {
                    String nome = metodo.getName();
                    if (args != null && args.length == 1 && args[0] instanceof String) {
                        Object v = valores.get(args[0]);
                        switch (nome) {
                            case "getInt":
                                return v == null ? 0 : ((Number) v).intValue();
                            case "getFloat":
                                return v == null ? 0f : ((Number) v).floatValue();
                            case "getString":
                            case "getDate":
                            case "getTimestamp":
                                return v;
                            default:
                                break;
                        }
                    }
                    throw new UnsupportedOperationException(nome);
                });
    }
}
