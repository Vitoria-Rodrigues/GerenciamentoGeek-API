package com.gerenciamentogeek.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class produtoDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Descrição é obrigatório")
    private String desc;

    @Positive(message = "O preço deve ser maior que 0")
    private Double preco;

    @Positive(message = "O produto precisa ter estoque para ser cadastrado")
    private int qtdEstoque;

    @Positive(message = "O código precisa ser maior que 0")
    private int codigo;

    @NotBlank(message = "Categoria é obrigatório")
    private Long categoria;

    public produtoDTO() {
    }

    public produtoDTO(String nome, String desc, Double preco, int qtdEstoque, int codigo, Long categoria) {
        this.nome = nome;
        this.desc = desc;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
        this.codigo = codigo;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Long getCategoria() {
        return categoria;
    }

    public void setCategoria(Long categoria) {
        this.categoria = categoria;
    }
}