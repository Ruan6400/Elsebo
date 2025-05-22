package com.example.demo.Utilitarios;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /*@Bean
    public JwtFiltroAutenticacao jwtFiltroAutenticacao() {
        return new JwtFiltroAutenticacao();
    }*/

    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFiltroAutenticacao jwtFiltroAutenticacao) throws Exception{
      return http.cors(Customizer.withDefaults()).csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> 
            auth
            .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
            .requestMatchers("public/login","/error").permitAll()
            .anyRequest().authenticated()
        ).addFilterBefore(jwtFiltroAutenticacao, UsernamePasswordAuthenticationFilter.class).build();

    }
}
