package com.example.demo.DTOs;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LivroDTO {
    private String titulo;
    private String autor;
    private String editora;
    private MultipartFile file;
}
