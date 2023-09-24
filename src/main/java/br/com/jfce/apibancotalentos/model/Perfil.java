package br.com.jfce.apibancotalentos.model;

import br.com.jfce.apibancotalentos.model.Enums.Genero;
import br.com.jfce.apibancotalentos.model.Enums.Raca;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Perfil extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "perfil_id")
    private String id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Genero genero;

    @NotNull
    private boolean isDeficiente;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Raca raca;

    @NotBlank
    private String habilidades;
}
