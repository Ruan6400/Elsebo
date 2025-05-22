package com.example.demo.controllers;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.example.demo.services.AutorService;
import com.example.demo.services.LivroService;
import com.example.demo.services.EditoraService;
import com.example.demo.services.CloudinaryService;
import com.example.demo.services.FuncionarioService;
import com.example.demo.services.CriptoService;
import com.example.demo.DTOs.LivroDTO;
import com.example.demo.Utilitarios.JwtUtil;
import com.example.demo.DTOs.FuncionarioDTO;
import com.example.demo.models.Autor;
import com.example.demo.models.Editora;
import com.example.demo.models.Gerente;
import com.example.demo.models.Operacional;
import com.example.demo.models.Funcionario;




@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
public class MainController {
    private final AutorService autorService;
    private final LivroService livroService;
    private final EditoraService editoraService;
    private final CloudinaryService cloudinaryService;
    private final FuncionarioService funcionarioService;
    private final CriptoService criptoService;

    public MainController(
        AutorService autorService,
        LivroService livroService,
        EditoraService editoraService,
        FuncionarioService funcionarioService,
        CloudinaryService cloudinaryService,
        CriptoService criptoService) {
        this.editoraService = editoraService;
        this.autorService = autorService;
        this.livroService = livroService;
        this.cloudinaryService = cloudinaryService;
        this.funcionarioService = funcionarioService;
        this.criptoService = criptoService;

    }
    public void nada(){
        
    }
    @PostMapping("/protected/autor")
    public String Adicionar(@RequestBody Autor autor){
        autorService.salvar(autor);
        return "Salvo";
    }

    @PostMapping("/protected/editora")
    public String AddEditora(@RequestBody Editora editora){
        editoraService.salvar(editora);
        return "Adicionado";
    }

    @PostMapping(value = "/protected/livro", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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
    
    @PostMapping("/protected/cadastrar")
    public String cadastrar(@RequestBody FuncionarioDTO funcionarioDTO) {
        if(funcionarioDTO.FaltaInformacao()) {
            return "Faltam informações!";
        }
        funcionarioDTO.setSenha(criptoService.codificar(funcionarioDTO.getSenha()));
        
        if(funcionarioDTO.getNivelCargo().equals("gerente")) {
            Funcionario funcionario = new Gerente(funcionarioDTO);
            funcionarioService.cadastrar(funcionario);
        } else if(funcionarioDTO.getNivelCargo().equals("operacional")) {
            Funcionario funcionario = new Operacional(funcionarioDTO);
            funcionarioService.cadastrar(funcionario);
        } else {
            return "Cargo inválido!";
        }
        return "Funcionario cadastrado com sucesso!";
    }

    @GetMapping("/public/login")
    public String login(){
        return JwtUtil.generateToken("admin", "ademir@email");
        //return "Login";
    }
}
