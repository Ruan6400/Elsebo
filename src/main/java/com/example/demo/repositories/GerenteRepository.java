package com.example.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.Gerente;

public interface GerenteRepository extends JpaRepository<Gerente, Long> {
    
}
