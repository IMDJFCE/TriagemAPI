package br.com.jfce.apibancotalentos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Raca {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "raca_id")
    private String id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Etnia etnia;

    @OneToMany(mappedBy = "raca")
    @JsonIgnore
    private List<Usuario> usuarios;

    public enum Etnia {
        AMARELO("Amarelo"),
        INDIGENA("Indígena"),
        BRANCO("Branco"),
        PARDO("Pardo"),
        PRETO("Preto");

        private final String etnia;

        Etnia(String etnia) {
            this.etnia = etnia;
        }
    }
}
