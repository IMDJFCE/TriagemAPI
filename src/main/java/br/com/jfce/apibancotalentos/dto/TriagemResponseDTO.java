package br.com.jfce.apibancotalentos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TriagemResponseDTO {
    private String id;

    @NotBlank
    @Size(max = 50)
    private String tipo;
}
