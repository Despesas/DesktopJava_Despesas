/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.despesa.dto.EntidadeBanco;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author uhitlei.barbosa
 */
@Entity
@Table(name = "usuario_server", catalog = "webtrunix_1", schema = "")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "UsuarioServer.findAll", query = "SELECT u FROM UsuarioServer u"),
  @NamedQuery(name = "UsuarioServer.findByCodigo", query = "SELECT u FROM UsuarioServer u WHERE u.codigo = :codigo"),
  @NamedQuery(name = "UsuarioServer.findByNome", query = "SELECT u FROM UsuarioServer u WHERE u.nome = :nome"),
  @NamedQuery(name = "UsuarioServer.findByUserSys", query = "SELECT u FROM UsuarioServer u WHERE u.userSys = :userSys"),
  @NamedQuery(name = "UsuarioServer.findBySenhaSys", query = "SELECT u FROM UsuarioServer u WHERE u.senhaSys = :senhaSys"),
  @NamedQuery(name = "UsuarioServer.findByUserServer", query = "SELECT u FROM UsuarioServer u WHERE u.userServer = :userServer"),
  @NamedQuery(name = "UsuarioServer.findBySenhaServer", query = "SELECT u FROM UsuarioServer u WHERE u.senhaServer = :senhaServer"),
  @NamedQuery(name = "UsuarioServer.findByPortaServer", query = "SELECT u FROM UsuarioServer u WHERE u.portaServer = :portaServer"),
  @NamedQuery(name = "UsuarioServer.findByUrlServer", query = "SELECT u FROM UsuarioServer u WHERE u.urlServer = :urlServer")})
public class UsuarioServer implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(nullable = false)
  private Integer codigo;
  @Column(length = 45)
  private String nome;
  @Column(name = "user_sys", length = 15)
  private String userSys;
  @Column(name = "senha_sys", length = 15)
  private String senhaSys;
  @Column(name = "user_server", length = 20)
  private String userServer;
  @Column(name = "senha_server", length = 20)
  private String senhaServer;
  @Column(name = "porta_server", length = 255)
  private String portaServer;
  @Column(name = "url_server", length = 60)
  private String urlServer;

  public UsuarioServer() {
  }

  public UsuarioServer(Integer codigo) {
    this.codigo = codigo;
  }

  public Integer getCodigo() {
    return codigo;
  }

  public void setCodigo(Integer codigo) {
    this.codigo = codigo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getUserSys() {
    return userSys;
  }

  public void setUserSys(String userSys) {
    this.userSys = userSys;
  }

  public String getSenhaSys() {
    return senhaSys;
  }

  public void setSenhaSys(String senhaSys) {
    this.senhaSys = senhaSys;
  }

  public String getUserServer() {
    return userServer;
  }

  public void setUserServer(String userServer) {
    this.userServer = userServer;
  }

  public String getSenhaServer() {
    return senhaServer;
  }

  public void setSenhaServer(String senhaServer) {
    this.senhaServer = senhaServer;
  }

  public String getPortaServer() {
    return portaServer;
  }

  public void setPortaServer(String portaServer) {
    this.portaServer = portaServer;
  }

  public String getUrlServer() {
    return urlServer;
  }

  public void setUrlServer(String urlServer) {
    this.urlServer = urlServer;
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
    if (!(object instanceof UsuarioServer)) {
      return false;
    }
    UsuarioServer other = (UsuarioServer) object;
    if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.projeto.despesa.dto.EntidadeBanco.UsuarioServer[ codigo=" + codigo + " ]";
  }
  
}
