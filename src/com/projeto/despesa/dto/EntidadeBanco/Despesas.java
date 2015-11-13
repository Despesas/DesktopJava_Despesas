/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.dto.EntidadeBanco;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author uhitlei.barbosa
 */
@Entity
@Table(catalog = "webtrunix_1", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Despesas.findAll", query = "SELECT d FROM Despesas d"),
  @NamedQuery(name = "Despesas.findByCodigo", query = "SELECT d FROM Despesas d WHERE d.codigo = :codigo"),
  @NamedQuery(name = "Despesas.findByDescricao", query = "SELECT d FROM Despesas d WHERE d.descricao = :descricao"),
  @NamedQuery(name = "Despesas.findByDataDesp", query = "SELECT d FROM Despesas d WHERE d.dataDesp = :dataDesp"),
  @NamedQuery(name = "Despesas.findByDataPag", query = "SELECT d FROM Despesas d WHERE d.dataPag = :dataPag"),
  @NamedQuery(name = "Despesas.findByParcelas", query = "SELECT d FROM Despesas d WHERE d.parcelas = :parcelas"),
  @NamedQuery(name = "Despesas.findByParcela", query = "SELECT d FROM Despesas d WHERE d.parcela = :parcela"),
  @NamedQuery(name = "Despesas.findByValorParcela", query = "SELECT d FROM Despesas d WHERE d.valorParcela = :valorParcela"),
  @NamedQuery(name = "Despesas.findByValor", query = "SELECT d FROM Despesas d WHERE d.valor = :valor"),
  @NamedQuery(name = "Despesas.findByValorRestante", query = "SELECT d FROM Despesas d WHERE d.valorRestante = :valorRestante"),
  @NamedQuery(name = "Despesas.findByStatus", query = "SELECT d FROM Despesas d WHERE d.status = :status"),
  @NamedQuery(name = "Despesas.findByObs", query = "SELECT d FROM Despesas d WHERE d.obs = :obs"),
  @NamedQuery(name = "Despesas.findByCodOrindo", query = "SELECT d FROM Despesas d WHERE d.codOrindo = :codOrindo"),
  @NamedQuery(name = "Despesas.findByCodUsuario", query = "SELECT d FROM Despesas d WHERE d.codUsuario = :codUsuario"),
  @NamedQuery(name = "Despesas.findByDatAlteracao", query = "SELECT d FROM Despesas d WHERE d.datAlteracao = :datAlteracao"),
  @NamedQuery(name = "Despesas.findByPago", query = "SELECT d FROM Despesas d WHERE d.pago = :pago"),
  @NamedQuery(name = "Despesas.findByDataPagamento", query = "SELECT d FROM Despesas d WHERE d.dataPagamento = :dataPagamento")})
public class Despesas implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(nullable = false)
  private Integer codigo;
  @Column(length = 50)
  private String descricao;
  @Column(name = "data_desp")
  @Temporal(TemporalType.DATE)
  private Date dataDesp;
  @Column(name = "data_pag")
  @Temporal(TemporalType.DATE)
  private Date dataPag;
  private Integer parcelas;
  private Integer parcela;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Column(name = "valor_parcela", precision = 12)
  private Float valorParcela;
  @Column(precision = 12)
  private Float valor;
  @Column(name = "valor_restante", precision = 12)
  private Float valorRestante;
  @Column(length = 30)
  private String status;
  @Column(length = 50)
  private String obs;
  @Basic(optional = false)
  @Column(name = "cod_orindo", nullable = false)
  private int codOrindo;
  @Basic(optional = false)
  @Column(name = "cod_usuario", nullable = false)
  private int codUsuario;
  @Column(name = "dat_alteracao")
  @Temporal(TemporalType.TIMESTAMP)
  private Date datAlteracao;
  private Integer pago;
  @Column(name = "data_pagamento")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataPagamento;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "despesas")
  private List<DespesasPagamento> despesasPagamentoList;

  public Despesas() {
  }

  public Despesas(Integer codigo) {
    this.codigo = codigo;
  }

  public Despesas(Integer codigo, int codOrindo, int codUsuario) {
    this.codigo = codigo;
    this.codOrindo = codOrindo;
    this.codUsuario = codUsuario;
  }

  public Integer getCodigo() {
    return codigo;
  }

  public void setCodigo(Integer codigo) {
    this.codigo = codigo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Date getDataDesp() {
    return dataDesp;
  }

  public void setDataDesp(Date dataDesp) {
    this.dataDesp = dataDesp;
  }

  public Date getDataPag() {
    return dataPag;
  }

  public void setDataPag(Date dataPag) {
    this.dataPag = dataPag;
  }

  public Integer getParcelas() {
    return parcelas;
  }

  public void setParcelas(Integer parcelas) {
    this.parcelas = parcelas;
  }

  public Integer getParcela() {
    return parcela;
  }

  public void setParcela(Integer parcela) {
    this.parcela = parcela;
  }

  public Float getValorParcela() {
    return valorParcela;
  }

  public void setValorParcela(Float valorParcela) {
    this.valorParcela = valorParcela;
  }

  public Float getValor() {
    return valor;
  }

  public void setValor(Float valor) {
    this.valor = valor;
  }

  public Float getValorRestante() {
    return valorRestante;
  }

  public void setValorRestante(Float valorRestante) {
    this.valorRestante = valorRestante;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getObs() {
    return obs;
  }

  public void setObs(String obs) {
    this.obs = obs;
  }

  public int getCodOrindo() {
    return codOrindo;
  }

  public void setCodOrindo(int codOrindo) {
    this.codOrindo = codOrindo;
  }

  public int getCodUsuario() {
    return codUsuario;
  }

  public void setCodUsuario(int codUsuario) {
    this.codUsuario = codUsuario;
  }

  public Date getDatAlteracao() {
    return datAlteracao;
  }

  public void setDatAlteracao(Date datAlteracao) {
    this.datAlteracao = datAlteracao;
  }

  public Integer getPago() {
    return pago;
  }

  public void setPago(Integer pago) {
    this.pago = pago;
  }

  public Date getDataPagamento() {
    return dataPagamento;
  }

  public void setDataPagamento(Date dataPagamento) {
    this.dataPagamento = dataPagamento;
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
    if (!(object instanceof Despesas)) {
      return false;
    }
    Despesas other = (Despesas) object;
    if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.projeto.despesa.dto.EntidadeBanco.Despesas[ codigo=" + codigo + " ]";
  }
  
}
