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

    public Livro criarLivro(String titulo, String nomeAutor, String nomeEditora,String capa_url) {
        Autor autor = autorRepository.findByNome(nomeAutor).orElseThrow(()->new RuntimeException("Autor não encontrado com nome "+nomeAutor));
        Editora editora = editoraRepository.findByNome(nomeEditora).orElseThrow(()->new RuntimeException("Editora não encontrada com nome "+nomeEditora));

        Livro livro = new Livro(titulo,autor,editora,capa_url);
        return livroRepository.save(livro);
    }
}
