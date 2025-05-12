package com.example.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Editora;
import java.util.Optional;

public interface EditoraRepository extends JpaRepository<Editora, Long> {

    @Query("SELECT e FROM Editora e WHERE e.nome = :nome")
    Optional<Editora>findByNome(String nome);
}
