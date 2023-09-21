package br.com.jfce.apibancotalentos.model;

import br.com.jfce.apibancotalentos.model.Enums.Genero;
import br.com.jfce.apibancotalentos.model.Enums.Raca;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Perfil extends AbstractEntity<Perfil>{

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

    @Override
    public void update(Perfil source) {
        this.genero = source.getGenero();
        this.isDeficiente = source.isDeficiente();
        this.raca = source.getRaca();
        this.habilidades = source.getHabilidades();
    }

    @Override
    public Perfil createNewInstance() {
        Perfil newInstance = new Perfil();
        newInstance.update(this);
        return newInstance;
    }
}
