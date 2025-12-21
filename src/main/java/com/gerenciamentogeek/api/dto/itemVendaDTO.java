package com.gerenciamentogeek.api.dto;

public class itemVendaDTO {
    private Long idProduto;
    private int qtdProduto;

    public itemVendaDTO() {
    }

    public itemVendaDTO(Long idProduto, int qtdProduto) {
        this.idProduto = idProduto;
        this.qtdProduto = qtdProduto;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public int getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(int qtdProduto) {
        this.qtdProduto = qtdProduto;
    }
}
