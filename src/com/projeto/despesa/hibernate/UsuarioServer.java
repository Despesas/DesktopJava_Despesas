package com.projeto.despesa.hibernate;
// Generated 01/07/2013 15:09:43 by Hibernate Tools 3.2.1.GA



/**
 * UsuarioServer generated by hbm2java
 */
public class UsuarioServer  implements java.io.Serializable {


     private Integer codigo;
     private String nome;
     private String userSys;
     private String senhaSys;
     private String userServer;
     private String senhaServer;
     private String portaServer;
     private String urlServer;

    public UsuarioServer() {
    }

    public UsuarioServer(String nome, String userSys, String senhaSys, String userServer, String senhaServer, String portaServer, String urlServer) {
       this.nome = nome;
       this.userSys = userSys;
       this.senhaSys = senhaSys;
       this.userServer = userServer;
       this.senhaServer = senhaServer;
       this.portaServer = portaServer;
       this.urlServer = urlServer;
    }
   
    public Integer getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getUserSys() {
        return this.userSys;
    }
    
    public void setUserSys(String userSys) {
        this.userSys = userSys;
    }
    public String getSenhaSys() {
        return this.senhaSys;
    }
    
    public void setSenhaSys(String senhaSys) {
        this.senhaSys = senhaSys;
    }
    public String getUserServer() {
        return this.userServer;
    }
    
    public void setUserServer(String userServer) {
        this.userServer = userServer;
    }
    public String getSenhaServer() {
        return this.senhaServer;
    }
    
    public void setSenhaServer(String senhaServer) {
        this.senhaServer = senhaServer;
    }
    public String getPortaServer() {
        return this.portaServer;
    }
    
    public void setPortaServer(String portaServer) {
        this.portaServer = portaServer;
    }
    public String getUrlServer() {
        return this.urlServer;
    }
    
    public void setUrlServer(String urlServer) {
        this.urlServer = urlServer;
    }




}

