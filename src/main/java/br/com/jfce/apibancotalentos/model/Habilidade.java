package br.com.jfce.apibancotalentos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Habilidade{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "habilidade_id")
    private String id;

    @NotBlank
    @Size(max = 50)
    private String habilidade;

    @NotBlank
    @Size(max = 2)
    private String peso;

    @ManyToMany(mappedBy = "habilidades")
    @JsonIgnore
    private Set<Perfil> perfis;
}
