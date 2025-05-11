package com.example.demo.services;

import org.springframework.stereotype.Service;
import com.example.demo.repositories.EditoraRepository;
import com.example.demo.models.Editora;

@Service
public class EditoraService {
    private final EditoraRepository editoraRepository;
    
    public EditoraService(EditoraRepository editoraRepository) {
        this.editoraRepository = editoraRepository;
    }
    public Editora salvar(Editora editora) {
        return editoraRepository.save(editora);
    }
}
