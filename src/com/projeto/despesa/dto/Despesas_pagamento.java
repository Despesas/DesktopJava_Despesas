package com.projeto.despesa.dto;

import java.sql.*; 



import java.math.BigInteger;



public class Despesas_pagamento {

public Despesas_pagamento(){
		
}

 private Integer forma_pagamento_codigo;
 private Integer despesas_codigo;
 private Timestamp data_pagamento;





 /** 
* @return the forma_pagamento_codigo
 */
public Integer getForma_pagamento_codigo(){
   return forma_pagamento_codigo;
}

  /** 
  * @param forma_pagamento_codigo the forma_pagamento_codigo to set 
   */
  public void setForma_pagamento_codigo(Integer forma_pagamento_codigo) {
    this.forma_pagamento_codigo = forma_pagamento_codigo;
  } 


 /** 
* @return the despesas_codigo
 */
public Integer getDespesas_codigo(){
   return despesas_codigo;
}

  /** 
  * @param despesas_codigo the despesas_codigo to set 
   */
  public void setDespesas_codigo(Integer despesas_codigo) {
    this.despesas_codigo = despesas_codigo;
  } 


 /** 
* @return the data_pagamento
 */
public Timestamp getData_pagamento(){
   return data_pagamento;
}

  /** 
  * @param data_pagamento the data_pagamento to set 
   */
  public void setData_pagamento(Timestamp data_pagamento) {
    this.data_pagamento = data_pagamento;
  } 




}