package com.projeto.despesa.hibernate;
// Generated 01/07/2013 15:09:43 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * TicketCard generated by hbm2java
 */
public class TicketCard  implements java.io.Serializable {


     private Integer ITicketCard;
     private int ITicketTipo;
     private String descricao;
     private String numeroCartao;
     private Double saldo;
     private Date dataAlteracao;
     private Date dataCriacao;

    public TicketCard() {
    }

	
    public TicketCard(int ITicketTipo) {
        this.ITicketTipo = ITicketTipo;
    }
    public TicketCard(int ITicketTipo, String descricao, String numeroCartao, Double saldo, Date dataAlteracao, Date dataCriacao) {
       this.ITicketTipo = ITicketTipo;
       this.descricao = descricao;
       this.numeroCartao = numeroCartao;
       this.saldo = saldo;
       this.dataAlteracao = dataAlteracao;
       this.dataCriacao = dataCriacao;
    }
   
    public Integer getITicketCard() {
        return this.ITicketCard;
    }
    
    public void setITicketCard(Integer ITicketCard) {
        this.ITicketCard = ITicketCard;
    }
    public int getITicketTipo() {
        return this.ITicketTipo;
    }
    
    public void setITicketTipo(int ITicketTipo) {
        this.ITicketTipo = ITicketTipo;
    }
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getNumeroCartao() {
        return this.numeroCartao;
    }
    
    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
    public Double getSaldo() {
        return this.saldo;
    }
    
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    public Date getDataAlteracao() {
        return this.dataAlteracao;
    }
    
    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
    public Date getDataCriacao() {
        return this.dataCriacao;
    }
    
    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }




}

