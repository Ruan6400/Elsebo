package com.example.demo.services;
import org.springframework.stereotype.Service;
import com.example.demo.models.Autor;
import com.example.demo.repositories.AutorRepository;


@Service
public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    public Autor salvar(Autor autor){
        return autorRepository.save(autor);
    }
}
