/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.dto.EntidadeBanco;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author uhitlei.barbosa
 */
@Entity
@Table(name = "forma_pagamento", catalog = "webtrunix_1", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "FormaPagamento.findAll", query = "SELECT f FROM FormaPagamento f"),
  @NamedQuery(name = "FormaPagamento.findByCodigo", query = "SELECT f FROM FormaPagamento f WHERE f.codigo = :codigo"),
  @NamedQuery(name = "FormaPagamento.findByNome", query = "SELECT f FROM FormaPagamento f WHERE f.nome = :nome"),
  @NamedQuery(name = "FormaPagamento.findByDataCadastro", query = "SELECT f FROM FormaPagamento f WHERE f.dataCadastro = :dataCadastro"),
  @NamedQuery(name = "FormaPagamento.findByDataAlteracao", query = "SELECT f FROM FormaPagamento f WHERE f.dataAlteracao = :dataAlteracao")})
public class FormaPagamento implements Serializable {
  @Transient
  private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(nullable = false)
  private Integer codigo;
  @Column(length = 45)
  private String nome;
  @Column(name = "data_cadastro")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataCadastro;
  @Column(name = "data_alteracao")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataAlteracao;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "formaPagamento")
  private List<DespesasPagamento> despesasPagamentoList;

  public FormaPagamento() {
  }

  public FormaPagamento(Integer codigo) {
    this.codigo = codigo;
  }

  public Integer getCodigo() {
    return codigo;
  }

  public void setCodigo(Integer codigo) {
    Integer oldCodigo = this.codigo;
    this.codigo = codigo;
    changeSupport.firePropertyChange("codigo", oldCodigo, codigo);
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    String oldNome = this.nome;
    this.nome = nome;
    changeSupport.firePropertyChange("nome", oldNome, nome);
  }

  public Date getDataCadastro() {
    return dataCadastro;
  }

  public void setDataCadastro(Date dataCadastro) {
    Date oldDataCadastro = this.dataCadastro;
    this.dataCadastro = dataCadastro;
    changeSupport.firePropertyChange("dataCadastro", oldDataCadastro, dataCadastro);
  }

  public Date getDataAlteracao() {
    return dataAlteracao;
  }

  public void setDataAlteracao(Date dataAlteracao) {
    Date oldDataAlteracao = this.dataAlteracao;
    this.dataAlteracao = dataAlteracao;
    changeSupport.firePropertyChange("dataAlteracao", oldDataAlteracao, dataAlteracao);
  }

  @XmlTransient
  public List<DespesasPagamento> getDespesasPagamentoList() {
    return despesasPagamentoList;
  }

  public void setDespesasPagamentoList(List<DespesasPagamento> despesasPagamentoList) {
    this.despesasPagamentoList = despesasPagamentoList;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (codigo != null ? codigo.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof FormaPagamento)) {
      return false;
    }
    FormaPagamento other = (FormaPagamento) object;
    if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.projeto.despesa.dto.EntidadeBanco.FormaPagamento[ codigo=" + codigo + " ]";
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    changeSupport.addPropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {
    changeSupport.removePropertyChangeListener(listener);
  }
  
}
