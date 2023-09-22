package br.com.jfce.apibancotalentos.model.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    PENDENTE("Pendente"),
    ENVIADO("Enviado");

    private final String status;
}
