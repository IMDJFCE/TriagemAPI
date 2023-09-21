package br.com.jfce.apibancotalentos.model.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Raca {
    AMARELO("Amarelo"),
    INDIGENA("Indígena"),
    BRANCO("Branco"),
    PARDO("Pardo"),
    PRETO("Preto");

    private final String raca;
}
