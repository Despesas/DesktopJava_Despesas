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
@Table(name = "ticket_instituicao", catalog = "webtrunix_1", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "TicketInstituicao.findAll", query = "SELECT t FROM TicketInstituicao t"),
  @NamedQuery(name = "TicketInstituicao.findByITicketInstituicao", query = "SELECT t FROM TicketInstituicao t WHERE t.iTicketInstituicao = :iTicketInstituicao"),
  @NamedQuery(name = "TicketInstituicao.findByNome", query = "SELECT t FROM TicketInstituicao t WHERE t.nome = :nome"),
  @NamedQuery(name = "TicketInstituicao.findByCnpj", query = "SELECT t FROM TicketInstituicao t WHERE t.cnpj = :cnpj"),
  @NamedQuery(name = "TicketInstituicao.findByDataCadastro", query = "SELECT t FROM TicketInstituicao t WHERE t.dataCadastro = :dataCadastro"),
  @NamedQuery(name = "TicketInstituicao.findByDataAlteracao", query = "SELECT t FROM TicketInstituicao t WHERE t.dataAlteracao = :dataAlteracao")})
public class TicketInstituicao implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "i_ticket_instituicao", nullable = false)
  private Integer iTicketInstituicao;
  @Basic(optional = false)
  @Column(nullable = false, length = 50)
  private String nome;
  @Column(length = 11)
  private String cnpj;
  @Column(name = "data_cadastro")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataCadastro;
  @Column(name = "data_alteracao")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataAlteracao;

  public TicketInstituicao() {
  }

  public TicketInstituicao(Integer iTicketInstituicao) {
    this.iTicketInstituicao = iTicketInstituicao;
  }

  public TicketInstituicao(Integer iTicketInstituicao, String nome) {
    this.iTicketInstituicao = iTicketInstituicao;
    this.nome = nome;
  }

  public Integer getITicketInstituicao() {
    return iTicketInstituicao;
  }

  public void setITicketInstituicao(Integer iTicketInstituicao) {
    this.iTicketInstituicao = iTicketInstituicao;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
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
    hash += (iTicketInstituicao != null ? iTicketInstituicao.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof TicketInstituicao)) {
      return false;
    }
    TicketInstituicao other = (TicketInstituicao) object;
    if ((this.iTicketInstituicao == null && other.iTicketInstituicao != null) || (this.iTicketInstituicao != null && !this.iTicketInstituicao.equals(other.iTicketInstituicao))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.projeto.despesa.dto.EntidadeBanco.TicketInstituicao[ iTicketInstituicao=" + iTicketInstituicao + " ]";
  }
  
}
