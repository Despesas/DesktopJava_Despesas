package com.java.geraDTO.classeGerada.bean;

import java.sql.*; 



public class Usuario_server {

public Usuario_server(){
		
}

 private Integer codigo;
 private String nome;
 private String user_sys;
 private String senha_sys;
 private String user_server;
 private String senha_server;
 private String porta_server;
 private String url_server;





 /** 
* @return the codigo
 */
public Integer getCodigo(){
   return codigo;
}

  /** 
  * @param codigo the codigo to set 
   */
  public void setCodigo(Integer codigo) {
    this.codigo = codigo;
  } 


 /** 
* @return the nome
 */
public String getNome(){
   return nome;
}

  /** 
  * @param nome the nome to set 
   */
  public void setNome(String nome) {
    this.nome = nome;
  } 


 /** 
* @return the user_sys
 */
public String getUser_sys(){
   return user_sys;
}

  /** 
  * @param user_sys the user_sys to set 
   */
  public void setUser_sys(String user_sys) {
    this.user_sys = user_sys;
  } 


 /** 
* @return the senha_sys
 */
public String getSenha_sys(){
   return senha_sys;
}

  /** 
  * @param senha_sys the senha_sys to set 
   */
  public void setSenha_sys(String senha_sys) {
    this.senha_sys = senha_sys;
  } 


 /** 
* @return the user_server
 */
public String getUser_server(){
   return user_server;
}

  /** 
  * @param user_server the user_server to set 
   */
  public void setUser_server(String user_server) {
    this.user_server = user_server;
  } 


 /** 
* @return the senha_server
 */
public String getSenha_server(){
   return senha_server;
}

  /** 
  * @param senha_server the senha_server to set 
   */
  public void setSenha_server(String senha_server) {
    this.senha_server = senha_server;
  } 


 /** 
* @return the porta_server
 */
public String getPorta_server(){
   return porta_server;
}

  /** 
  * @param porta_server the porta_server to set 
   */
  public void setPorta_server(String porta_server) {
    this.porta_server = porta_server;
  } 


 /** 
* @return the url_server
 */
public String getUrl_server(){
   return url_server;
}

  /** 
  * @param url_server the url_server to set 
   */
  public void setUrl_server(String url_server) {
    this.url_server = url_server;
  } 




}