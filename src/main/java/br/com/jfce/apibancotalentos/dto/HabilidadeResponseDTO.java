package br.com.jfce.apibancotalentos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HabilidadeResponseDTO {
    private String id;

    @NotBlank
    private String habilidade;

    @NotBlank
    private String peso;
}
