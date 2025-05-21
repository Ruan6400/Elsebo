package com.example.demo.models;
import jakarta.persistence.*;

@Entity
public class Operacional extends Funcionario {
    public Operacional(String nome, String cpf, String telefone, String email, String endereco, String cargo,
            String senha) {
        super(nome, cpf, telefone, email, endereco, cargo, senha);
    }

    public Operacional() {
    }
}
