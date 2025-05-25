package com.example.demo.models;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.*;

@Getter
@Setter
@ConfigurationProperties(prefix = "dev")
@Component
public class ADM {
    

    private String password;

    private String login;
}
