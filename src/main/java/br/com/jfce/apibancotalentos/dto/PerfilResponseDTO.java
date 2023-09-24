package br.com.jfce.apibancotalentos.dto;

import br.com.jfce.apibancotalentos.model.Enums.Genero;
import br.com.jfce.apibancotalentos.model.Enums.Raca;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PerfilResponseDTO {
    private String id;

    @NotNull
    private Genero genero;

    @NotNull
    private boolean isDeficiente;

    @NotNull
    private Raca raca;

    @NotBlank
    private String habilidades;
}
