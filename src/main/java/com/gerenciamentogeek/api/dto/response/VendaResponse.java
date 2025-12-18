package com.gerenciamentogeek.api.dto.response;

import com.gerenciamentogeek.api.classes.Produto;

import java.time.LocalDate;
import java.util.List;

public class VendaResponse {
    private Long id;
    private LocalDate data;
    private Double total;
    private int qtd;
    private String cpfCliente;
    private Long idFuncionario;
    private List<Produto> produtos;
    private String formaPagamento;
    private int parcelasPagamento;

    public VendaResponse() {
    }

    public VendaResponse(Long id, LocalDate data, Double total, int qtd, String cpfCliente, Long idFuncionario, List<Produto> produtos, String formaPagamento, int parcelasPagamento) {
        this.id = id;
        this.data = data;
        this.total = total;
        this.qtd = qtd;
        this.cpfCliente = cpfCliente;
        this.idFuncionario = idFuncionario;
        this.produtos = produtos;
        this.formaPagamento = formaPagamento;
        this.parcelasPagamento = parcelasPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public int getParcelasPagamento() {
        return parcelasPagamento;
    }

    public void setParcelasPagamento(int parcelasPagamento) {
        this.parcelasPagamento = parcelasPagamento;
    }
}
