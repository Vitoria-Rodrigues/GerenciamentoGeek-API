package com.gerenciamentogeek.api.repository;

import com.gerenciamentogeek.api.classes.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
