package com.example.demo.services;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class CriptoService {
    private final PasswordEncoder passwordEncoder;

    public CriptoService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String codificar(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean verificar(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    
}
