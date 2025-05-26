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
import com.example.demo.DTOs.LoginDTO;
import com.example.demo.Utilitarios.JwtUtil;
import com.example.demo.DTOs.FuncionarioDTO;
import com.example.demo.models.ADM;
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
    private final ADM adm;

    public MainController(
        AutorService autorService,
        LivroService livroService,
        EditoraService editoraService,
        FuncionarioService funcionarioService,
        CloudinaryService cloudinaryService,
        CriptoService criptoService,
        ADM adm) {
        this.editoraService = editoraService;
        this.autorService = autorService;
        this.livroService = livroService;
        this.cloudinaryService = cloudinaryService;
        this.funcionarioService = funcionarioService;
        this.criptoService = criptoService;
        this.adm = adm;
        adm.setPassword(); // Set the password for ADM
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
        /*if(funcionarioDTO.FaltaInformacao()) {
            return "Faltam informações!";
        }*/
        funcionarioDTO.setSenha(criptoService.codificar(funcionarioDTO.getSenha()));


        Class<? extends Funcionario> tipoFuncionario;


        if(funcionarioDTO.getNivelCargo().equals("gerente")) {
            tipoFuncionario = Gerente.class;
        } else if(funcionarioDTO.getNivelCargo().equals("operacional")) {
            tipoFuncionario = Operacional.class;
        } else {
            return "Cargo inválido!";
        }
        funcionarioService.cadastrar(funcionarioService.DTO(funcionarioDTO, tipoFuncionario));
        return "Funcionario cadastrado com sucesso!";
    }

    @PostMapping("/public/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        if(adm.isADM(loginDTO.getUser(),loginDTO.getPassword())){
            return JwtUtil.generateToken("admin", loginDTO.getUser());
        }else{
            Operacional operacional = funcionarioService.findByEmailOpr(loginDTO.getUser()).orElse(null);
            Gerente gerente = funcionarioService.findByEmailGer(loginDTO.getUser()).orElse(null);

            if(operacional != null){
                if(criptoService.verificar(loginDTO.getPassword(), gerente.getSenha())){
                    return JwtUtil.generateToken(gerente.getNome(),gerente.getEmail());
                }else{return "Usuário ou senha inválidos!";}
            }else if(gerente != null){
                if(criptoService.verificar(loginDTO.getPassword(), gerente.getSenha())){
                    return JwtUtil.generateToken(gerente.getNome(),gerente.getEmail());
                }else{return "Usuário ou senha inválidos!";}
            }else{
                return "Usuário ou senha inválidos!";
            }
        }



      
        //return "login";
    }
}
