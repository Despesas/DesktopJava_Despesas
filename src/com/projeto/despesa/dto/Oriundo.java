package com.projeto.despesa.dto;

import java.sql.*; 



public class Oriundo {

public Oriundo(){
		
}

 private Integer codigo;
 private String descricao;
 private Date data_cadastro;
 private Integer dia_fechamento;
 private Integer dia_pag;





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
* @return the descricao
 */
public String getDescricao(){
   return descricao;
}

  /** 
  * @param descricao the descricao to set 
   */
  public void setDescricao(String descricao) {
    this.descricao = descricao;
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
* @return the dia_fechamento
 */
public Integer getDia_fechamento(){
   return dia_fechamento;
}

  /** 
  * @param dia_fechamento the dia_fechamento to set 
   */
  public void setDia_fechamento(Integer dia_fechamento) {
    this.dia_fechamento = dia_fechamento;
  } 


 /** 
* @return the dia_pag
 */
public Integer getDia_pag(){
   return dia_pag;
}

  /** 
  * @param dia_pag the dia_pag to set 
   */
  public void setDia_pag(Integer dia_pag) {
    this.dia_pag = dia_pag;
  } 




}