package br.com.jfce.apibancotalentos.model;

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
    private String id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private eRaca raca;

    public enum eRaca {
        AMARELO("Amarelo"),
        INDIGENA("Indígena"),
        BRANCO("Branco"),
        PARDO("Pardo"),
        PRETO("Preto");

        private final String raca;

        eRaca(String raca) {
            this.raca = raca;
        }
    }
}
