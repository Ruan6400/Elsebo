package com.example.demo.services;
import org.springframework.stereotype.Service;
import com.example.demo.models.Funcionario;
import com.example.demo.models.Operacional;
import com.example.demo.models.Gerente;
import com.example.demo.repositories.OperacionalRepository;
import com.example.demo.repositories.GerenteRepository;

@Service
public class FuncionarioService {
    private final OperacionalRepository operacionalRepository;
    private final GerenteRepository gerenteRepository;

    public FuncionarioService(OperacionalRepository operacionalRepository, GerenteRepository gerenteRepository) {
        this.operacionalRepository = operacionalRepository;
        this.gerenteRepository = gerenteRepository;
    }
    public void cadastrarFuncionario(Funcionario funcionario) {
        if (funcionario instanceof Operacional) {
            operacionalRepository.save((Operacional) funcionario);
        } else if (funcionario instanceof Gerente) {
            gerenteRepository.save((Gerente) funcionario);
        }else{
            throw new IllegalArgumentException("Tipo de funcionário desconhecido");
        }
    }
}
