package br.com.jfce.apibancotalentos.model.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Genero {
    MASCULINO("M"),
    FEMININO("F");

    private final String genero;
}
