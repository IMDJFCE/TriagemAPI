package br.com.jfce.apibancotalentos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OportunidadeRequestDTO {
    @NotBlank
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
    private String email;

    @NotBlank
    private String informacoes;
}
