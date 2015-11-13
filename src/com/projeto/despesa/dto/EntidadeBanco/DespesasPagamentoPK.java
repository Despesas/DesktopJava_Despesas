/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.dto.EntidadeBanco;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author uhitlei.barbosa
 */
@Embeddable
public class DespesasPagamentoPK implements Serializable {
  @Basic(optional = false)
  @Column(name = "forma_pagamento_codigo", nullable = false)
  private int formaPagamentoCodigo;
  @Basic(optional = false)
  @Column(name = "despesas_codigo", nullable = false)
  private int despesasCodigo;

  public DespesasPagamentoPK() {
  }

  public DespesasPagamentoPK(int formaPagamentoCodigo, int despesasCodigo) {
    this.formaPagamentoCodigo = formaPagamentoCodigo;
    this.despesasCodigo = despesasCodigo;
  }

  public int getFormaPagamentoCodigo() {
    return formaPagamentoCodigo;
  }

  public void setFormaPagamentoCodigo(int formaPagamentoCodigo) {
    this.formaPagamentoCodigo = formaPagamentoCodigo;
  }

  public int getDespesasCodigo() {
    return despesasCodigo;
  }

  public void setDespesasCodigo(int despesasCodigo) {
    this.despesasCodigo = despesasCodigo;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (int) formaPagamentoCodigo;
    hash += (int) despesasCodigo;
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof DespesasPagamentoPK)) {
      return false;
    }
    DespesasPagamentoPK other = (DespesasPagamentoPK) object;
    if (this.formaPagamentoCodigo != other.formaPagamentoCodigo) {
      return false;
    }
    if (this.despesasCodigo != other.despesasCodigo) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.projeto.despesa.dto.EntidadeBanco.DespesasPagamentoPK[ formaPagamentoCodigo=" + formaPagamentoCodigo + ", despesasCodigo=" + despesasCodigo + " ]";
  }
  
}
