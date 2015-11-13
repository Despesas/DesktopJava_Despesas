/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.dto.EntidadeBanco;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "ticket_card", catalog = "webtrunix_1", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "TicketCard.findAll", query = "SELECT t FROM TicketCard t"),
  @NamedQuery(name = "TicketCard.findByITicketCard", query = "SELECT t FROM TicketCard t WHERE t.iTicketCard = :iTicketCard"),
  @NamedQuery(name = "TicketCard.findByITicketTipo", query = "SELECT t FROM TicketCard t WHERE t.iTicketTipo = :iTicketTipo"),
  @NamedQuery(name = "TicketCard.findByDescricao", query = "SELECT t FROM TicketCard t WHERE t.descricao = :descricao"),
  @NamedQuery(name = "TicketCard.findByNumeroCartao", query = "SELECT t FROM TicketCard t WHERE t.numeroCartao = :numeroCartao"),
  @NamedQuery(name = "TicketCard.findBySaldo", query = "SELECT t FROM TicketCard t WHERE t.saldo = :saldo"),
  @NamedQuery(name = "TicketCard.findByDataAlteracao", query = "SELECT t FROM TicketCard t WHERE t.dataAlteracao = :dataAlteracao"),
  @NamedQuery(name = "TicketCard.findByDataCriacao", query = "SELECT t FROM TicketCard t WHERE t.dataCriacao = :dataCriacao")})
public class TicketCard implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "i_ticket_card", nullable = false)
  private Integer iTicketCard;
  @Basic(optional = false)
  @Column(name = "i_ticket_tipo", nullable = false)
  private int iTicketTipo;
  @Column(length = 30)
  private String descricao;
  @Column(name = "numero_cartao", length = 18)
  private String numeroCartao;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Column(precision = 22)
  private Double saldo;
  @Column(name = "data_alteracao")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataAlteracao;
  @Column(name = "data_criacao")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataCriacao;

  public TicketCard() {
  }

  public TicketCard(Integer iTicketCard) {
    this.iTicketCard = iTicketCard;
  }

  public TicketCard(Integer iTicketCard, int iTicketTipo) {
    this.iTicketCard = iTicketCard;
    this.iTicketTipo = iTicketTipo;
  }

  public Integer getITicketCard() {
    return iTicketCard;
  }

  public void setITicketCard(Integer iTicketCard) {
    this.iTicketCard = iTicketCard;
  }

  public int getITicketTipo() {
    return iTicketTipo;
  }

  public void setITicketTipo(int iTicketTipo) {
    this.iTicketTipo = iTicketTipo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public String getNumeroCartao() {
    return numeroCartao;
  }

  public void setNumeroCartao(String numeroCartao) {
    this.numeroCartao = numeroCartao;
  }

  public Double getSaldo() {
    return saldo;
  }

  public void setSaldo(Double saldo) {
    this.saldo = saldo;
  }

  public Date getDataAlteracao() {
    return dataAlteracao;
  }

  public void setDataAlteracao(Date dataAlteracao) {
    this.dataAlteracao = dataAlteracao;
  }

  public Date getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(Date dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (iTicketCard != null ? iTicketCard.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof TicketCard)) {
      return false;
    }
    TicketCard other = (TicketCard) object;
    if ((this.iTicketCard == null && other.iTicketCard != null) || (this.iTicketCard != null && !this.iTicketCard.equals(other.iTicketCard))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.projeto.despesa.dto.EntidadeBanco.TicketCard[ iTicketCard=" + iTicketCard + " ]";
  }
  
}
