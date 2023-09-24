package br.com.jfce.apibancotalentos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TriagemResponseDTO {
    private String id;

    @NotBlank
    private String tipo;
}
