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
public class Oportunidade extends AbstractEntity{
    private String titulo;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private String descricao;
    private String triagem;
    private String habilidades;
    private String email;
    private String informacoes;
}
