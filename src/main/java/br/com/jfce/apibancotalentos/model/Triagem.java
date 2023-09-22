package br.com.jfce.apibancotalentos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Triagem extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "triagem_id")
    private String id;

    @NotBlank(message = "O campo tipo não pode estar em branco.")
    @Size(max = 50, message = "O campo tipo deve ter no máximo 50 caracteres.")
    private String tipo;
}
