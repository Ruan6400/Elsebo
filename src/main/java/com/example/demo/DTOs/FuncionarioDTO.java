package com.example.demo.DTOs;

import com.example.demo.models.Funcionario;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FuncionarioDTO extends Funcionario{
    private String nivelCargo;
    public Boolean FaltaInformacao(){
        return 
            this.getNome() == null ||
            this.getCpf() == null ||
            this.getEmail() == null ||
            this.getSenha() == null ||
            this.getNivelCargo() == null ||
            this.getTelefone() == null;
    }
}
