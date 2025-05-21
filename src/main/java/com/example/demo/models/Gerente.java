package com.example.demo.models;
import jakarta.persistence.*;

@Entity
public class Gerente extends Funcionario{
    public Gerente(String nome, String cpf, String telefone, String email, String endereco, String cargo,
            String senha) {
        super(nome, cpf, telefone, email, endereco, cargo, senha);
    }
    public Gerente() {
    }
}
