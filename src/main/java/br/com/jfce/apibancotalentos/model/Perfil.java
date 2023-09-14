package br.com.jfce.apibancotalentos.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Perfil extends AbstractEntity{

    private String nome;
    private LocalDate dataNascimento;
    private Genero genero;
    private boolean isDeficiente;
    private String etinia;
}
