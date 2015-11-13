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
@Table(catalog = "webtrunix_1", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Oriundo.findAll", query = "SELECT o FROM Oriundo o"),
  @NamedQuery(name = "Oriundo.findByCodigo", query = "SELECT o FROM Oriundo o WHERE o.codigo = :codigo"),
  @NamedQuery(name = "Oriundo.findByDescricao", query = "SELECT o FROM Oriundo o WHERE o.descricao = :descricao"),
  @NamedQuery(name = "Oriundo.findByDataCadastro", query = "SELECT o FROM Oriundo o WHERE o.dataCadastro = :dataCadastro"),
  @NamedQuery(name = "Oriundo.findByDiaFechamento", query = "SELECT o FROM Oriundo o WHERE o.diaFechamento = :diaFechamento"),
  @NamedQuery(name = "Oriundo.findByDiaPag", query = "SELECT o FROM Oriundo o WHERE o.diaPag = :diaPag")})
public class Oriundo implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(nullable = false)
  private Integer codigo;
  @Column(length = 30)
  private String descricao;
  @Column(name = "data_cadastro")
  @Temporal(TemporalType.DATE)
  private Date dataCadastro;
  @Column(name = "dia_fechamento")
  private Integer diaFechamento;
  @Column(name = "dia_pag")
  private Integer diaPag;

  public Oriundo() {
  }

  public Oriundo(Integer codigo) {
    this.codigo = codigo;
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

  public Date getDataCadastro() {
    return dataCadastro;
  }

  public void setDataCadastro(Date dataCadastro) {
    this.dataCadastro = dataCadastro;
  }

  public Integer getDiaFechamento() {
    return diaFechamento;
  }

  public void setDiaFechamento(Integer diaFechamento) {
    this.diaFechamento = diaFechamento;
  }

  public Integer getDiaPag() {
    return diaPag;
  }

  public void setDiaPag(Integer diaPag) {
    this.diaPag = diaPag;
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
    if (!(object instanceof Oriundo)) {
      return false;
    }
    Oriundo other = (Oriundo) object;
    if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.projeto.despesa.dto.EntidadeBanco.Oriundo[ codigo=" + codigo + " ]";
  }
  
}
