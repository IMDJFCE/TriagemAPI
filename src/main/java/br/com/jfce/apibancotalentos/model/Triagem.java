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

    @NotBlank
    @Size(max = 50)
    private String tipo;
}
