package br.com.jfce.apibancotalentos.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioResponseDTO {
    private String id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private String matricula;
    private String tipo;
}
