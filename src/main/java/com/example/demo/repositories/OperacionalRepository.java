package com.example.demo.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.Operacional;

public interface OperacionalRepository extends JpaRepository<Operacional, Long> {

    @Query("SELECT o FROM Operacional o WHERE o.email = :usuario")
    public Optional<Operacional> findByEmail(@Param("usuario") String usuario);
}
