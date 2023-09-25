package br.com.jfce.apibancotalentos.dto;

import br.com.jfce.apibancotalentos.model.Enums.Genero;
import br.com.jfce.apibancotalentos.model.Enums.Raca;
import br.com.jfce.apibancotalentos.model.Habilidade;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class PerfilRequestDTO {
    @NotNull
    private Genero genero;

    @NotNull
    private boolean isDeficiente;

    @NotNull
    private Raca raca;

    private Set<Habilidade> habilidades;
}
