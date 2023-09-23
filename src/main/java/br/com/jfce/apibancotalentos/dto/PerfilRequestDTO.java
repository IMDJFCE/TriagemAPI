package br.com.jfce.apibancotalentos.dto;

import br.com.jfce.apibancotalentos.model.Enums.Genero;
import br.com.jfce.apibancotalentos.model.Enums.Raca;
import lombok.Data;

@Data
public class PerfilRequestDTO {
    private Genero genero;
    private boolean isDeficiente;
    private Raca raca;
    private String habilidades;
}
