package com.gerenciamentogeek.api.repository;

import com.gerenciamentogeek.api.classes.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
