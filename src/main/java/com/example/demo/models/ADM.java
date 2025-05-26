package com.example.demo.models;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "dev")
@Component
public class ADM {
    

    private String password;

    private String login;

    public Boolean isADM(String login, String password) {
        return this.login.equals(login) && this.password.equals(password);
    }
}
