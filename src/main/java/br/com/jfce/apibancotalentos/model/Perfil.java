package br.com.jfce.apibancotalentos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Perfil {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String dataNascimento;
    private char genero;
    private boolean isDeficiente;
    private String etinia;
    private String[] habilidadesTecncia;
    private String[] habilidadesComportamentais;
}
