package com.example.demo.services;
import org.springframework.stereotype.Service;

import com.example.demo.models.Livro;
import com.example.demo.models.Autor;
import com.example.demo.models.Editora;
import com.example.demo.repositories.AutorRepository;
import com.example.demo.repositories.LivroRepository;
import com.example.demo.repositories.EditoraRepository;

@Service
public class LivroService {
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final EditoraRepository editoraRepository;

    public LivroService(LivroRepository livroRepository,AutorRepository autorRepository, EditoraRepository editoraRepository) {
        this.editoraRepository = editoraRepository;
        this.autorRepository = autorRepository;
        this.livroRepository  = livroRepository;
    }

    public Livro criarLivro(String titulo, Long autorId, Long editoraId) {
        Autor autor = autorRepository.findById(autorId).orElseThrow(()->new RuntimeException("Autor não encontrado com id "+autorId));
        Editora editora = editoraRepository.findById(editoraId).orElseThrow(()->new RuntimeException("Editora não encontrada com id "+editoraId));

        Livro livro = new Livro(titulo,autor,editora);
        return livroRepository.save(livro);
    }
}
