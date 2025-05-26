package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.example.demo.Utilitarios.JwtFiltroAutenticacao;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<JwtFiltroAutenticacao> jwtFilter() {
		FilterRegistrationBean<JwtFiltroAutenticacao> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtFiltroAutenticacao());
		registrationBean.addUrlPatterns("/protected/*");
		return registrationBean;
	}

	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
