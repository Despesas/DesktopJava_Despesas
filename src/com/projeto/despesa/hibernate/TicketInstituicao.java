package com.projeto.despesa.hibernate;
// Generated 01/07/2013 15:09:43 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * TicketInstituicao generated by hbm2java
 */
public class TicketInstituicao  implements java.io.Serializable {


     private Integer ITicketInstituicao;
     private String nome;
     private String cnpj;
     private Date dataCadastro;
     private Date dataAlteracao;

    public TicketInstituicao() {
    }

	
    public TicketInstituicao(String nome) {
        this.nome = nome;
    }
    public TicketInstituicao(String nome, String cnpj, Date dataCadastro, Date dataAlteracao) {
       this.nome = nome;
       this.cnpj = cnpj;
       this.dataCadastro = dataCadastro;
       this.dataAlteracao = dataAlteracao;
    }
   
    public Integer getITicketInstituicao() {
        return this.ITicketInstituicao;
    }
    
    public void setITicketInstituicao(Integer ITicketInstituicao) {
        this.ITicketInstituicao = ITicketInstituicao;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCnpj() {
        return this.cnpj;
    }
    
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public Date getDataCadastro() {
        return this.dataCadastro;
    }
    
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    public Date getDataAlteracao() {
        return this.dataAlteracao;
    }
    
    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }




}


