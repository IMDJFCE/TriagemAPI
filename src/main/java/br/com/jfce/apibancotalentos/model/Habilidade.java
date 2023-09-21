package br.com.jfce.apibancotalentos.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Habilidade extends AbstractEntity<Habilidade>{
    @NotBlank(message = "O campo habilidade não pode estar em branco.")
    @Size(max = 50, message = "O campo habilidade deve ter no máximo 50 caracteres.")
    private String habilidade;


    @Override
    public void update(Habilidade source) {
        this.habilidade = source.getHabilidade();
    }

    @Override
    public Habilidade createNewInstance() {
        Habilidade newInstance = new Habilidade();
        newInstance.update(this);
        return newInstance;
    }
}
