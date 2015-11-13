package com.java.geraDTO.classeGerada.bean;

import java.sql.*; 



public class Usuario {

public Usuario(){
		
}

 private Integer codigo;
 private String nome;
 private String usuario;
 private String senha;
 private Integer nivel;





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
* @return the usuario
 */
public String getUsuario(){
   return usuario;
}

  /** 
  * @param usuario the usuario to set 
   */
  public void setUsuario(String usuario) {
    this.usuario = usuario;
  } 


 /** 
* @return the senha
 */
public String getSenha(){
   return senha;
}

  /** 
  * @param senha the senha to set 
   */
  public void setSenha(String senha) {
    this.senha = senha;
  } 


 /** 
* @return the nivel
 */
public Integer getNivel(){
   return nivel;
}

  /** 
  * @param nivel the nivel to set 
   */
  public void setNivel(Integer nivel) {
    this.nivel = nivel;
  } 




}