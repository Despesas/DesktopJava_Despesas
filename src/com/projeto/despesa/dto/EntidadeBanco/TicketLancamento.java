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
@Table(name = "ticket_lancamento", catalog = "webtrunix_1", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "TicketLancamento.findAll", query = "SELECT t FROM TicketLancamento t"),
  @NamedQuery(name = "TicketLancamento.findByITicketLancamento", query = "SELECT t FROM TicketLancamento t WHERE t.iTicketLancamento = :iTicketLancamento"),
  @NamedQuery(name = "TicketLancamento.findByTipo", query = "SELECT t FROM TicketLancamento t WHERE t.tipo = :tipo"),
  @NamedQuery(name = "TicketLancamento.findByITicketCard", query = "SELECT t FROM TicketLancamento t WHERE t.iTicketCard = :iTicketCard"),
  @NamedQuery(name = "TicketLancamento.findByIInstituicao", query = "SELECT t FROM TicketLancamento t WHERE t.iInstituicao = :iInstituicao"),
  @NamedQuery(name = "TicketLancamento.findByValor", query = "SELECT t FROM TicketLancamento t WHERE t.valor = :valor"),
  @NamedQuery(name = "TicketLancamento.findByDataLancamento", query = "SELECT t FROM TicketLancamento t WHERE t.dataLancamento = :dataLancamento"),
  @NamedQuery(name = "TicketLancamento.findByDataAlteracao", query = "SELECT t FROM TicketLancamento t WHERE t.dataAlteracao = :dataAlteracao"),
  @NamedQuery(name = "TicketLancamento.findBySaldoAnterior", query = "SELECT t FROM TicketLancamento t WHERE t.saldoAnterior = :saldoAnterior"),
  @NamedQuery(name = "TicketLancamento.findByValidado", query = "SELECT t FROM TicketLancamento t WHERE t.validado = :validado"),
  @NamedQuery(name = "TicketLancamento.findByILastLancamento", query = "SELECT t FROM TicketLancamento t WHERE t.iLastLancamento = :iLastLancamento"),
  @NamedQuery(name = "TicketLancamento.findByMesRef", query = "SELECT t FROM TicketLancamento t WHERE t.mesRef = :mesRef"),
  @NamedQuery(name = "TicketLancamento.findByAnoRef", query = "SELECT t FROM TicketLancamento t WHERE t.anoRef = :anoRef")})
public class TicketLancamento implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "i_ticket_lancamento", nullable = false)
  private Integer iTicketLancamento;
  @Basic(optional = false)
  @Column(nullable = false)
  private int tipo;
  @Basic(optional = false)
  @Column(name = "i_ticket_card", nullable = false)
  private int iTicketCard;
  @Basic(optional = false)
  @Column(name = "i_instituicao", nullable = false)
  private int iInstituicao;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Column(precision = 22)
  private Double valor;
  @Column(name = "data_lancamento")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataLancamento;
  @Column(name = "data_alteracao")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataAlteracao;
  @Column(name = "saldo_anterior", precision = 22)
  private Double saldoAnterior;
  private Integer validado;
  @Column(name = "i_last_lancamento")
  private Integer iLastLancamento;
  @Column(name = "mes_ref")
  private Integer mesRef;
  @Column(name = "ano_ref")
  private Integer anoRef;

  public TicketLancamento() {
  }

  public TicketLancamento(Integer iTicketLancamento) {
    this.iTicketLancamento = iTicketLancamento;
  }

  public TicketLancamento(Integer iTicketLancamento, int tipo, int iTicketCard, int iInstituicao) {
    this.iTicketLancamento = iTicketLancamento;
    this.tipo = tipo;
    this.iTicketCard = iTicketCard;
    this.iInstituicao = iInstituicao;
  }

  public Integer getITicketLancamento() {
    return iTicketLancamento;
  }

  public void setITicketLancamento(Integer iTicketLancamento) {
    this.iTicketLancamento = iTicketLancamento;
  }

  public int getTipo() {
    return tipo;
  }

  public void setTipo(int tipo) {
    this.tipo = tipo;
  }

  public int getITicketCard() {
    return iTicketCard;
  }

  public void setITicketCard(int iTicketCard) {
    this.iTicketCard = iTicketCard;
  }

  public int getIInstituicao() {
    return iInstituicao;
  }

  public void setIInstituicao(int iInstituicao) {
    this.iInstituicao = iInstituicao;
  }

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public Date getDataLancamento() {
    return dataLancamento;
  }

  public void setDataLancamento(Date dataLancamento) {
    this.dataLancamento = dataLancamento;
  }

  public Date getDataAlteracao() {
    return dataAlteracao;
  }

  public void setDataAlteracao(Date dataAlteracao) {
    this.dataAlteracao = dataAlteracao;
  }

  public Double getSaldoAnterior() {
    return saldoAnterior;
  }

  public void setSaldoAnterior(Double saldoAnterior) {
    this.saldoAnterior = saldoAnterior;
  }

  public Integer getValidado() {
    return validado;
  }

  public void setValidado(Integer validado) {
    this.validado = validado;
  }

  public Integer getILastLancamento() {
    return iLastLancamento;
  }

  public void setILastLancamento(Integer iLastLancamento) {
    this.iLastLancamento = iLastLancamento;
  }

  public Integer getMesRef() {
    return mesRef;
  }

  public void setMesRef(Integer mesRef) {
    this.mesRef = mesRef;
  }

  public Integer getAnoRef() {
    return anoRef;
  }

  public void setAnoRef(Integer anoRef) {
    this.anoRef = anoRef;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (iTicketLancamento != null ? iTicketLancamento.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof TicketLancamento)) {
      return false;
    }
    TicketLancamento other = (TicketLancamento) object;
    if ((this.iTicketLancamento == null && other.iTicketLancamento != null) || (this.iTicketLancamento != null && !this.iTicketLancamento.equals(other.iTicketLancamento))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.projeto.despesa.dto.EntidadeBanco.TicketLancamento[ iTicketLancamento=" + iTicketLancamento + " ]";
  }
  
}
