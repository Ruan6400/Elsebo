package com.example.demo.models;
import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
@Entity
public class Livro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String capa_url;


    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "editora_id", nullable = false)
    private Editora editora;

    public Livro(){}
    public Livro(String titulo,Autor autor, Editora editora, String capa_url){
        this.editora = editora;
        this.titulo = titulo;
        this.autor = autor;
        this.capa_url = capa_url;
    }

}
