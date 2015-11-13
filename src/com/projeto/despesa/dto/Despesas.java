package com.projeto.despesa.dto;

import java.sql.*; 



public class Despesas {

public Despesas(){
		
}

 private Integer codigo;
 private String descricao;
 private Date data_desp;
 private Date data_pag;
 private Integer parcelas;
 private Integer parcela;
 private Float valor_parcela;
 private Float valor;
 //private Float valor_restante;
 private String status;
 private String obs;
 private Integer cod_orindo;
 private Integer cod_usuario;
 private String sql;
 





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
* @return the data_desp
 */
public Date getData_desp(){
   return data_desp;
}

  /** 
  * @param data_desp the data_desp to set 
   */
  public void setData_desp(Date data_desp) {
    this.data_desp = data_desp;
  } 


 /** 
* @return the data_pag
 */
public Date getData_pag(){
   return data_pag;
}

  /** 
  * @param data_pag the data_pag to set 
   */
  public void setData_pag(Date data_pag) {
    this.data_pag = data_pag;
  } 


 /** 
* @return the parcelas
 */
public Integer getParcelas(){
   return parcelas;
}

  /** 
  * @param parcelas the parcelas to set 
   */
  public void setParcelas(Integer parcelas) {
    this.parcelas = parcelas;
  } 


 /** 
* @return the parcela
 */
public Integer getParcela(){
   return parcela;
}

  /** 
  * @param parcela the parcela to set 
   */
  public void setParcela(Integer parcela) {
    this.parcela = parcela;
  } 


 /** 
* @return the valor_parcela
 */
public Float getValor_parcela(){
   return valor_parcela;
}

  /** 
  * @param valor_parcela the valor_parcela to set 
   */
  public void setValor_parcela(Float valor_parcela) {
    this.valor_parcela = valor_parcela;
  } 


 /** 
* @return the valor
 */
public Float getValor(){
   return valor;
}

  /** 
  * @param valor the valor to set 
   */
  public void setValor(Float valor) {
    this.valor = valor;
  } 


 /** 
* @return the status
 */
public String getStatus(){
   return status;
}

  /** 
  * @param status the status to set 
   */
  public void setStatus(String status) {
    this.status = status;
  } 


 /** 
* @return the obs
 */
public String getObs(){
   return obs;
}

  /** 
  * @param obs the obs to set 
   */
  public void setObs(String obs) {
    this.obs = obs;
  } 


 /** 
* @return the cod_orindo
 */
public Integer getCod_orindo(){
   return cod_orindo;
}

  /** 
  * @param cod_orindo the cod_orindo to set 
   */
  public void setCod_orindo(Integer cod_orindo) {
    this.cod_orindo = cod_orindo;
  } 


 /** 
* @return the cod_usuario
 */
public Integer getCod_usuario(){
   return cod_usuario;
}

  /** 
  * @param cod_usuario the cod_usuario to set 
   */
  public void setCod_usuario(Integer cod_usuario) {
    this.cod_usuario = cod_usuario;
  }

  /**
   * @return the sql
   */
  public String getSql() {
    return sql;
  }

  /**
   * @param sql the sql to set
   */
  public void setSql(String sql) {
    this.sql = sql;
  }

    /**
     * @return the valor_restante
     */
//    public Float getValor_restante() {
//        return valor_restante;
//    }
//
//    /**
//     * @param valor_restante the valor_restante to set
//     */
//    public void setValor_restante(Float valor_restante) {
//        this.valor_restante = valor_restante;
//    }




}