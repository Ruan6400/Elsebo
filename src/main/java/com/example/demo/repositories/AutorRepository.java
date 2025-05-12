package com.example.demo.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.Autor;


public interface AutorRepository extends JpaRepository<Autor, Long>{
    @Query("SELECT a FROM Autor a WHERE a.nome = :nome")
    Optional<Autor> findByNome(@Param("nome") String nome);
}
