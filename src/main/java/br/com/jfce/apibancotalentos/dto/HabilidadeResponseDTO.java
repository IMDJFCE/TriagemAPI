package br.com.jfce.apibancotalentos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HabilidadeResponseDTO {
    private String id;

    @NotBlank
    @Size(max = 50)
    private String habilidade;

    @NotBlank
    @Size(max = 2)
    private String peso;
}
