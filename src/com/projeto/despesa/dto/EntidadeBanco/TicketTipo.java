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
@Table(name = "ticket_tipo", catalog = "webtrunix_1", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "TicketTipo.findAll", query = "SELECT t FROM TicketTipo t"),
  @NamedQuery(name = "TicketTipo.findByITicketTipo", query = "SELECT t FROM TicketTipo t WHERE t.iTicketTipo = :iTicketTipo"),
  @NamedQuery(name = "TicketTipo.findByDescricao", query = "SELECT t FROM TicketTipo t WHERE t.descricao = :descricao"),
  @NamedQuery(name = "TicketTipo.findByDataCadastro", query = "SELECT t FROM TicketTipo t WHERE t.dataCadastro = :dataCadastro"),
  @NamedQuery(name = "TicketTipo.findByDataAlteracao", query = "SELECT t FROM TicketTipo t WHERE t.dataAlteracao = :dataAlteracao")})
public class TicketTipo implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "i_ticket_tipo", nullable = false)
  private Integer iTicketTipo;
  @Column(length = 45)
  private String descricao;
  @Column(name = "data_cadastro")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataCadastro;
  @Column(name = "data_alteracao")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataAlteracao;

  public TicketTipo() {
  }

  public TicketTipo(Integer iTicketTipo) {
    this.iTicketTipo = iTicketTipo;
  }

  public Integer getITicketTipo() {
    return iTicketTipo;
  }

  public void setITicketTipo(Integer iTicketTipo) {
    this.iTicketTipo = iTicketTipo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Date getDataCadastro() {
    return dataCadastro;
  }

  public void setDataCadastro(Date dataCadastro) {
    this.dataCadastro = dataCadastro;
  }

  public Date getDataAlteracao() {
    return dataAlteracao;
  }

  public void setDataAlteracao(Date dataAlteracao) {
    this.dataAlteracao = dataAlteracao;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (iTicketTipo != null ? iTicketTipo.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof TicketTipo)) {
      return false;
    }
    TicketTipo other = (TicketTipo) object;
    if ((this.iTicketTipo == null && other.iTicketTipo != null) || (this.iTicketTipo != null && !this.iTicketTipo.equals(other.iTicketTipo))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.projeto.despesa.dto.EntidadeBanco.TicketTipo[ iTicketTipo=" + iTicketTipo + " ]";
  }
  
}
