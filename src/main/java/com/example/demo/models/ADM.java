package com.example.demo.models;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.example.demo.services.CriptoService;

@ConfigurationProperties(prefix = "dev")
@Component
public class ADM {
    private final CriptoService criptoService = new CriptoService();
    private String password;

    private String login;

    public Boolean isADM(String login, String password) {
        return this.login.equals(login) && criptoService.verificar(password, this.password);
    }

    public void setPassword() {
        
        this.password = criptoService.codificar(this.password);
    }
}
