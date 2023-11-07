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
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "genero_id")
    private String id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private eGenero genero;

    public enum eGenero {
        MASCULINO("M"),
        FEMININO("F"),
        OUTROS("O");

        private final String genero;

        eGenero(String genero){
            this.genero = genero;
        }
    }
}
