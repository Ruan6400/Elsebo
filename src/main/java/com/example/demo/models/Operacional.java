package com.example.demo.models;
import com.example.demo.DTOs.FuncionarioDTO;

import jakarta.persistence.*;

@Entity
public class Operacional extends Funcionario {
    public Operacional(String nome, String cpf, String telefone, String email, String endereco, String cargo,
            String senha) {
        super(nome, cpf, telefone, email, endereco, cargo, senha);
    }

    public Operacional() {
    }
    public Operacional(FuncionarioDTO funcionarioDTO) {
        setNome(funcionarioDTO.getNome());
        setCpf(funcionarioDTO.getCpf());
        setTelefone(funcionarioDTO.getTelefone());
        setEmail(funcionarioDTO.getEmail());
        setEndereco(funcionarioDTO.getEndereco());
        setCargo(funcionarioDTO.getCargo());
        setSenha(funcionarioDTO.getSenha());
    }
}
