package br.com.jfce.apibancotalentos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OportunidadeRequestDTO {
    @NotBlank
    @Size(max = 100)
    private String titulo;

    @NotNull
    private LocalDate dataInicial;

    @NotNull
    private LocalDate dataFinal;

    @NotBlank
    private String descricao;

    @NotBlank
    private String triagem;

    @NotBlank
    private String habilidades;

    @NotBlank
    @Email
    @Size(max = 70)
    private String email;

    @NotBlank
    private String informacoes;
}
