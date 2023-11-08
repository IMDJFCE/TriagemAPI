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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "raca_id")
    @JsonIgnore
    private String id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Descricao descricao;

    public enum Descricao {
        AMARELO("Amarelo"),
        INDIGENA("Indígena"),
        BRANCO("Branco"),
        PARDO("Pardo"),
        PRETO("Preto");

        private final String descricao;

        Descricao(String descricao) {
            this.descricao = descricao;
        }
    }
}
