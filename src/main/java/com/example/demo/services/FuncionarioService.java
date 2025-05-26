package com.example.demo.services;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.example.demo.models.Funcionario;
import com.example.demo.models.Operacional;
import com.example.demo.models.Gerente;
import com.example.demo.repositories.OperacionalRepository;
import com.example.demo.repositories.GerenteRepository;
import com.example.demo.DTOs.FuncionarioDTO;

@Service
public class FuncionarioService {
    private final OperacionalRepository operacionalRepository;
    private final GerenteRepository gerenteRepository;
    private final ModelMapper modelMapper;

    public FuncionarioService(
        OperacionalRepository operacionalRepository,
        GerenteRepository gerenteRepository,
        ModelMapper modelMapper) {
        this.operacionalRepository = operacionalRepository;
        this.gerenteRepository = gerenteRepository;
        this.modelMapper = modelMapper;
    }
    public void cadastrar(Funcionario funcionario) {
        switch (funcionario.getClass().getSimpleName()) {
            case "Operacional":
                operacionalRepository.save((Operacional) funcionario);
                break;
            case "Gerente":
                gerenteRepository.save((Gerente) funcionario);
                break;
            default:
                throw new IllegalArgumentException("Tipo de funcionário desconhecido: ");
        }
    }
    public <T extends Funcionario> T DTO(FuncionarioDTO funcionarioDTO,Class<T> tipo){
        return modelMapper.map(funcionarioDTO, tipo);
    }

}
