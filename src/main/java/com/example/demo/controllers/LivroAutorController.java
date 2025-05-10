package com.example.demo.controllers;
import org.springframework.web.bind.annotation.*;

import com.example.demo.services.AutorService;
import com.example.demo.services.LivroService;
import com.example.demo.DTOs.LivroDTO;
import com.example.demo.models.Autor;




@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
public class LivroAutorController {
    private final AutorService autorService;
    private final LivroService livroService;

    public LivroAutorController(AutorService autorService,LivroService livroService){
        this.autorService = autorService;
        this.livroService = livroService;
    }

    @PostMapping("/autor")
    public String Adicionar(@RequestBody Autor autor){
        autorService.salvar(autor);
        return "Salvo";
    }

    @PostMapping("/livro")
    public String AddLivro(@RequestBody LivroDTO livroDTO){
        livroService.criarLivro(livroDTO.getTitulo(),livroDTO.getAutorId());
        return "Adicionado";
    }
    
}
