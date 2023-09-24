package br.com.jfce.apibancotalentos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioRequestDTO {
    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    @NotNull
    private LocalDate dataNascimento;

    @NotBlank
    private String matricula;

    @NotBlank
    private String tipo;
}
