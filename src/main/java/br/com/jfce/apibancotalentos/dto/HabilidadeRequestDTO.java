package br.com.jfce.apibancotalentos.dto;

import br.com.jfce.apibancotalentos.model.Habilidade;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HabilidadeRequestDTO {
    @NotBlank
    @Size(max = 50)
    private String habilidade;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Habilidade.Tipo tipo;
}
