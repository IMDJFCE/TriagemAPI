package br.com.jfce.apibancotalentos.model;

import jakarta.persistence.*;
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
public class Habilidade extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "habilidade_id")
    private String id;

    @NotBlank(message = "O campo habilidade não pode estar em branco.")
    @Size(max = 50, message = "O campo habilidade deve ter no máximo 50 caracteres.")
    private String habilidade;

}
