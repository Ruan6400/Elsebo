package com.example.demo.services;
import org.springframework.stereotype.Service;

import com.example.demo.models.Livro;
import com.example.demo.models.Autor;
import com.example.demo.repositories.AutorRepository;
import com.example.demo.repositories.LivroRepository;

@Service
public class LivroService {
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public LivroService(LivroRepository livroRepository,AutorRepository autorRepository){
        this.autorRepository = autorRepository;
        this.livroRepository  = livroRepository;
    }

    public Livro criarLivro(String titulo, Long autorId){
        Autor autor = autorRepository.findById(autorId)
            .orElseThrow(()->new RuntimeException("Autor não encontrado com id"+autorId));
        Livro livro = new Livro(titulo,autor);
        return livroRepository.save(livro);
    }
}
