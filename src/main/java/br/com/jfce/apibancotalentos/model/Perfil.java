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
    @NotNull(message = "O campo gênero não pode ser nulo.")
    private Genero genero;

    @NotNull(message = "O campo deficiente não pode ser nulo.")
    private boolean isDeficiente;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo raça não pode ser nulo.")
    private Raca raca;

    @NotBlank(message = "O campo habilidades não pode estar em branco.")
    private String habilidades;
}
