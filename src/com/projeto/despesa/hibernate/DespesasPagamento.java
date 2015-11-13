package com.projeto.despesa.hibernate;
// Generated 01/07/2013 15:09:43 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * DespesasPagamento generated by hbm2java
 */
public class DespesasPagamento  implements java.io.Serializable {


     private DespesasPagamentoId id;
     private Despesas despesas;
     private FormaPagamento formaPagamento;
     private Date dataPagamento;

    public DespesasPagamento() {
    }

	
    public DespesasPagamento(DespesasPagamentoId id, Despesas despesas, FormaPagamento formaPagamento) {
        this.id = id;
        this.despesas = despesas;
        this.formaPagamento = formaPagamento;
    }
    public DespesasPagamento(DespesasPagamentoId id, Despesas despesas, FormaPagamento formaPagamento, Date dataPagamento) {
       this.id = id;
       this.despesas = despesas;
       this.formaPagamento = formaPagamento;
       this.dataPagamento = dataPagamento;
    }
   
    public DespesasPagamentoId getId() {
        return this.id;
    }
    
    public void setId(DespesasPagamentoId id) {
        this.id = id;
    }
    public Despesas getDespesas() {
        return this.despesas;
    }
    
    public void setDespesas(Despesas despesas) {
        this.despesas = despesas;
    }
    public FormaPagamento getFormaPagamento() {
        return this.formaPagamento;
    }
    
    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    public Date getDataPagamento() {
        return this.dataPagamento;
    }
    
    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }




}

