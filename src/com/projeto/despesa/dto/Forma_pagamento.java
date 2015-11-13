package com.projeto.despesa.dto;

import java.sql.*; 



import java.math.BigInteger;



public class Forma_pagamento {

public Forma_pagamento(){
		
}

 private Integer codigo;
 private String nome;
 private Date data_cadastro;
 private Timestamp data_alteracao;





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
* @return the data_cadastro
 */
public Date getData_cadastro(){
   return data_cadastro;
}

  /** 
  * @param data_cadastro the data_cadastro to set 
   */
  public void setData_cadastro(Date data_cadastro) {
    this.data_cadastro = data_cadastro;
  } 


 /** 
* @return the data_alteracao
 */
public Timestamp getData_alteracao(){
   return data_alteracao;
}

  /** 
  * @param data_alteracao the data_alteracao to set 
   */
  public void setData_alteracao(Timestamp data_alteracao) {
    this.data_alteracao = data_alteracao;
  } 




}