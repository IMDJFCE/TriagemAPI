package br.com.jfce.apibancotalentos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "oportunidade_id")
    private String id;

    @NotBlank
    @Size(max = 100)
    private String titulo;

    @NotNull
    private LocalDate dataInicial;

    @NotNull
    private LocalDate dataFinal;

    @NotBlank
    private String descricao;

    @NotBlank
    private String triagem;

    @NotBlank
    private String habilidades;

    @Email
    @NotBlank
    @Size(max = 70)
    private String email;

    @NotBlank
    private String informacoes;
}
