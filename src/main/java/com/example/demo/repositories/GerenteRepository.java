package com.example.demo.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.Gerente;

public interface GerenteRepository extends JpaRepository<Gerente, Long> {
    @Query("SELECT o FROM Gerente o WHERE o.email = :usuario")
    public Optional<Gerente> findByEmail(@Param("usuario") String usuario);
}
