package com.example.demo.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.example.demo.services.AutorService;
import com.example.demo.services.LivroService;
import com.example.demo.services.EditoraService;
import com.example.demo.services.CloudinaryService;
import com.example.demo.DTOs.LivroDTO;
import com.example.demo.models.Autor;
import com.example.demo.models.Editora;




@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
public class MainController {
    private final AutorService autorService;
    private final LivroService livroService;
    private final EditoraService editoraService;
    private final CloudinaryService cloudinaryService;

    public MainController(
        AutorService autorService,
        LivroService livroService,
        EditoraService editoraService,
        CloudinaryService cloudinaryService) {
        this.editoraService = editoraService;
        this.autorService = autorService;
        this.livroService = livroService;
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping("/autor")
    public String Adicionar(@RequestBody Autor autor){
        autorService.salvar(autor);
        return "Salvo";
    }

    @PostMapping("/editora")
    public String AddEditora(@RequestBody Editora editora){
        editoraService.salvar(editora);
        return "Adicionado";
    }

    @PostMapping(value = "/livro", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String AddLivro(@ModelAttribute LivroDTO livroDTO){
        try{
            String url ="";
            if(livroDTO.getFile() != null) {
                url = cloudinaryService.uploadFile(livroDTO.getFile(),livroDTO.getEditora(), livroDTO.getAutor());
            }
            livroService.criarLivro(
            livroDTO.getTitulo(),
            livroDTO.getAutor(),
            livroDTO.getEditora(),
            url);
            return "Adicionado";
        }catch (Exception e){
            e.printStackTrace();
            return "Erro ao cadastar o livro";
        }
        /**/
    }
    
}
