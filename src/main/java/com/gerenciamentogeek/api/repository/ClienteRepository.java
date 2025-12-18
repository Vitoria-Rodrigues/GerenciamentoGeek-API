package com.gerenciamentogeek.api.repository;

import com.gerenciamentogeek.api.classes.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT c FROM Cliente c WHERE c.cpfC = :cpf")
    Optional<Cliente> buscarPorCpf(String cpf);
}
