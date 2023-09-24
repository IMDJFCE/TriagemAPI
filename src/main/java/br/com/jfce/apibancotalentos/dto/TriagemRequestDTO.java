package br.com.jfce.apibancotalentos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TriagemRequestDTO {
    @NotBlank
    private String tipo;
}
