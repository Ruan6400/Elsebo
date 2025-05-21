package com.example.demo.DTOs;

import com.example.demo.models.Funcionario;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FuncionarioDTO extends Funcionario{
    private String nivelCargo;
}
