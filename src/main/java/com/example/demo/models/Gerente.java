package com.example.demo.models;
import jakarta.persistence.*;
import com.example.demo.DTOs.FuncionarioDTO;

@Entity
public class Gerente extends Funcionario{
    public Gerente(String nome, String cpf, String telefone, String email, String endereco, String cargo,
            String senha) {
        super(nome, cpf, telefone, email, endereco, cargo, senha);
    }
    public Gerente() {
    }
    public Gerente(FuncionarioDTO funcionarioDTO) {
        setNome(funcionarioDTO.getNome());
        setCpf(funcionarioDTO.getCpf());
        setTelefone(funcionarioDTO.getTelefone());
        setEmail(funcionarioDTO.getEmail());
        setEndereco(funcionarioDTO.getEndereco());
        setCargo(funcionarioDTO.getCargo());
        setSenha(funcionarioDTO.getSenha());
    }
}
