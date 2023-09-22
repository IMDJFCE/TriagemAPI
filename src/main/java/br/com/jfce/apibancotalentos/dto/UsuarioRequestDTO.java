package br.com.jfce.apibancotalentos.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioRequestDTO {
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    private String matricula;
    private String tipo;
}
