/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.utilitarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.Properties;

import java.util.PropertyResourceBundle;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WILL
 */
public class conexao {

    public String url;
    public String driver;
    public String usuario;
    public String senha;
    public PropertyResourceBundle config1;
    public ResourceBundle config;
    private Connection con;
    public PreparedStatement pstm;
    public ResultSet rs;
    public DatabaseMetaData metaData;
    public Properties properties;
    public InputStream fis;

    //config.getString("url"),config.getString("driver"),config.getString("usuario"),config.getString("senha")
    public conexao() {

        lerPropriedades();

        // getInit();
    }

    private void lerPropriedades() {

        this.properties = new Properties();

        try {
            //Setamos o arquivo que será lido
            this.fis = new FileInputStream(new File("c://Despesas/lib/bancodedados.properties").getAbsoluteFile());
//             InputStream fis =  getClass().getClassLoader().getResourceAsStream("/com/projeto/despesa/utilitarios/bancodedados.properties");
            //método load faz a leitura através do objeto fis
            this.config = new PropertyResourceBundle(fis);
            this.properties.load(fis);
            
            getInit();
              fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void getInit() {
       
        try {
            
            this.url ="jdbc:mysql:"+ config.getString("url")+":"+config.getString("porta.banco");
            this.driver = config.getString("driver");
            this.usuario = config.getString("usuario");
            this.senha = config.getString("senha");
           
        }
        catch(MissingResourceException ex){
            System.out.println("Erro: " + ex.getMessage());
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception ex){
            System.out.println("Erro: " + ex.getMessage());
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        }

//      conectar();
//      System.out.println("CLASSE CONEXAO: "+config.getString("local"));
    }

    public Connection conectar(String url, String driver, String usuario, String senha) {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, usuario, senha);
            System.out.println("CONECTADO!");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Class: Conexao: Nao foi possivel encontrar o DRIVER");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Class: Conexao: Nao foi possivel Conectar ao Banco");
        }
        return con;
    }

    public void desconectar() {
        try {
            con.close();
            System.out.println("DESCONECTADO!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Class: Conexao: Nao foi possivel Desconectar");
        }
    }
}
