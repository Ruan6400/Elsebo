package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@MappedSuperclass
public abstract class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String endereco;
    private String cargo;
    private String senha;
    public Funcionario(String nome, String cpf, String telefone, String email, String endereco, String cargo,
            String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.cargo = cargo;
        this.senha = senha;
    }
    public Funcionario() {
    }
}
