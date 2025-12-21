package com.gerenciamentogeek.api.services;

import com.gerenciamentogeek.api.classes.Categoria;
import com.gerenciamentogeek.api.dto.response.CategoriaResponse;
import com.gerenciamentogeek.api.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaResponse> listarCategorisa() {
        List<Categoria> categorias = categoriaRepository.findAll();
        List<CategoriaResponse> listaCargos = new ArrayList<>();

        if(categorias.isEmpty()){
            throw new RuntimeException("Categoria não encontrado");
        } else {
            for(Categoria categoria : categorias){
                CategoriaResponse categoriaResponse = new CategoriaResponse(
                        categoria.getId(),
                        categoria.getNomeCateg()
                );
                listaCargos.add(categoriaResponse);
            }
            return listaCargos;
        }
    }

    public CategoriaResponse buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrado"));
        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNomeCateg()
        );
    }
}

