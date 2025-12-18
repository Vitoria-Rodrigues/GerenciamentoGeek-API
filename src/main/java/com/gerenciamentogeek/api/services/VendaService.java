package com.gerenciamentogeek.api.services;

import com.gerenciamentogeek.api.classes.*;
import com.gerenciamentogeek.api.dto.itemVendaDTO;
import com.gerenciamentogeek.api.dto.response.VendaResponse;
import com.gerenciamentogeek.api.dto.vendaDTO;
import com.gerenciamentogeek.api.repository.ClienteRepository;
import com.gerenciamentogeek.api.repository.FuncionarioRepository;
import com.gerenciamentogeek.api.repository.ProdutoRepository;
import com.gerenciamentogeek.api.repository.VendaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VendaService {
    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ClienteRepository clienteRepository;

    public VendaService(VendaRepository vendaRepository, ProdutoRepository produtoRepository, FuncionarioRepository funcionarioRepository, ClienteRepository clienteRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.clienteRepository = clienteRepository;
    }

    public VendaResponse cadastrarVenda(vendaDTO vendaDTO) {
        Cliente cliente = clienteRepository.buscarPorCpf(vendaDTO.getCpfCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Funcionario funcionario = funcionarioRepository.findById(vendaDTO.getIdFuncionario())
                .orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));

        FormaPagamento formapag = new FormaPagamento(vendaDTO.getFormaPagamento(), vendaDTO.getParcelasPagamento());

        List<Produto> listaProduto = new ArrayList<>();

        for(itemVendaDTO itemVendaDTO : vendaDTO.getItemVenda()){
            Produto produto = produtoRepository.findById(itemVendaDTO.getIdProduto())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            if(produto.getQtdEstoque() <= 0){
                throw new RuntimeException("Produto não em estoque");
            }
            listaProduto.add(produto);
        }

        Venda venda = new Venda();
        venda.setDataVenda(LocalDate.now());
        venda.setTotalVenda(vendaDTO.getTotal());
        venda.setQtdVenda(vendaDTO.getQtd());
        venda.setCliente(cliente);
        venda.setFuncionario(funcionario);
        venda.setFormapagamento(formapag);
        venda.setProd(listaProduto);

        Venda vendaSalva = vendaRepository.save(venda);

        return new VendaResponse(
                vendaSalva.getId(),
                vendaSalva.getDataVenda(),
                vendaSalva.getTotalVenda(),
                vendaSalva.getQtdVenda(),
                vendaSalva.getCliente().getCpfC(),
                vendaSalva.getFuncionario().getId(),
                vendaSalva.getProd(),
                vendaSalva.getFormapagamento().getMetodoPag(),
                vendaSalva.getFormapagamento().getParcelasPag()
        );
    }

    public List<VendaResponse> listarVendas(String nomeCliente) {
        List<VendaResponse> listaVenda = new ArrayList<>();

        if (nomeCliente.isEmpty()) {
            List<Venda> vendas = vendaRepository.findAll();
            if (vendas.isEmpty()) {
                throw new RuntimeException("Venda não encontrada");
            } else {
                for (Venda venda : vendas) {
                    VendaResponse vendaResponse = new VendaResponse(
                            venda.getId(),
                            venda.getDataVenda(),
                            venda.getTotalVenda(),
                            venda.getQtdVenda(),
                            venda.getCliente().getCpfC(),
                            venda.getFuncionario().getId(),
                            venda.getProd(),
                            venda.getFormapagamento().getMetodoPag(),
                            venda.getFormapagamento().getParcelasPag()
                    );
                    listaVenda.add(vendaResponse);
                }
            }
        } else {
            Venda venda = vendaRepository.listarVendaPorNomeCliente(nomeCliente).orElseThrow(() -> new RuntimeException("Nome de cliente não encontrado"));
            VendaResponse vendaResponse = new VendaResponse(
                    venda.getId(),
                    venda.getDataVenda(),
                    venda.getTotalVenda(),
                    venda.getQtdVenda(),
                    venda.getCliente().getCpfC(),
                    venda.getFuncionario().getId(),
                    venda.getProd(),
                    venda.getFormapagamento().getMetodoPag(),
                    venda.getFormapagamento().getParcelasPag()
            );
            listaVenda.add(vendaResponse);
        }

        return listaVenda;
    }





























}
