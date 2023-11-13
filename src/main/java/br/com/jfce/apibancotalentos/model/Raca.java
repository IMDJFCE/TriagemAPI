package br.com.jfce.apibancotalentos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Raca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "raca_id")
    @JsonIgnore
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Descricao descricao;

    public enum Descricao {
        AMARELA("Amarela"),
        INDIGENA("Indígena"),
        BRANCA("Branca"),
        PARDA("Parda"),
        PRETA("Preta");

        private final String descricao;

        Descricao(String descricao) {
            this.descricao = descricao;
        }
    }
}
