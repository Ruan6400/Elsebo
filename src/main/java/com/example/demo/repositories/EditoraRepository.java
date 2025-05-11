package com.example.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.Editora;

public interface EditoraRepository extends JpaRepository<Editora, Long> {
}
