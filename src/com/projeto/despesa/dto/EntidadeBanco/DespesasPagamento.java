/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.dto.EntidadeBanco;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author uhitlei.barbosa
 */
@Entity
@Table(name = "despesas_pagamento", catalog = "webtrunix_1", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "DespesasPagamento.findAll", query = "SELECT d FROM DespesasPagamento d"),
  @NamedQuery(name = "DespesasPagamento.findByFormaPagamentoCodigo", query = "SELECT d FROM DespesasPagamento d WHERE d.despesasPagamentoPK.formaPagamentoCodigo = :formaPagamentoCodigo"),
  @NamedQuery(name = "DespesasPagamento.findByDespesasCodigo", query = "SELECT d FROM DespesasPagamento d WHERE d.despesasPagamentoPK.despesasCodigo = :despesasCodigo"),
  @NamedQuery(name = "DespesasPagamento.findByDataPagamento", query = "SELECT d FROM DespesasPagamento d WHERE d.dataPagamento = :dataPagamento")})
public class DespesasPagamento implements Serializable {
  private static final long serialVersionUID = 1L;
  @EmbeddedId
  protected DespesasPagamentoPK despesasPagamentoPK;
  @Column(name = "data_pagamento")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataPagamento;
  @JoinColumn(name = "forma_pagamento_codigo", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
  @ManyToOne(optional = false)
  private FormaPagamento formaPagamento;
  @JoinColumn(name = "despesas_codigo", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
  @ManyToOne(optional = false)
  private Despesas despesas;

  public DespesasPagamento() {
  }

  public DespesasPagamento(DespesasPagamentoPK despesasPagamentoPK) {
    this.despesasPagamentoPK = despesasPagamentoPK;
  }

  public DespesasPagamento(int formaPagamentoCodigo, int despesasCodigo) {
    this.despesasPagamentoPK = new DespesasPagamentoPK(formaPagamentoCodigo, despesasCodigo);
  }

  public DespesasPagamentoPK getDespesasPagamentoPK() {
    return despesasPagamentoPK;
  }

  public void setDespesasPagamentoPK(DespesasPagamentoPK despesasPagamentoPK) {
    this.despesasPagamentoPK = despesasPagamentoPK;
  }

  public Date getDataPagamento() {
    return dataPagamento;
  }

  public void setDataPagamento(Date dataPagamento) {
    this.dataPagamento = dataPagamento;
  }

  public FormaPagamento getFormaPagamento() {
    return formaPagamento;
  }

  public void setFormaPagamento(FormaPagamento formaPagamento) {
    this.formaPagamento = formaPagamento;
  }

  public Despesas getDespesas() {
    return despesas;
  }

  public void setDespesas(Despesas despesas) {
    this.despesas = despesas;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (despesasPagamentoPK != null ? despesasPagamentoPK.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof DespesasPagamento)) {
      return false;
    }
    DespesasPagamento other = (DespesasPagamento) object;
    if ((this.despesasPagamentoPK == null && other.despesasPagamentoPK != null) || (this.despesasPagamentoPK != null && !this.despesasPagamentoPK.equals(other.despesasPagamentoPK))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.projeto.despesa.dto.EntidadeBanco.DespesasPagamento[ despesasPagamentoPK=" + despesasPagamentoPK + " ]";
  }
  
}
