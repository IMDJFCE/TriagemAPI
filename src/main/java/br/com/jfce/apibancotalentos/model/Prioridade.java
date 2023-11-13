package br.com.jfce.apibancotalentos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Prioridade {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "prioridade_id")
    private String id;

    private String ordem;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "oportunidade_id")
    private Oportunidade oportunidade;

    @ManyToMany
    @JoinTable(
            name = "deficiencia_prioridade",
            joinColumns = {@JoinColumn(name = "id_prioridade", referencedColumnName = "prioridade_id")},
            inverseJoinColumns = {@JoinColumn(name = "id_deficiencia", referencedColumnName = "deficiencia_id")}
    )
    private Set<Deficiencia> habilidades;

    @ManyToMany
    @JoinTable(
            name = "raca_prioridade",
            joinColumns = {@JoinColumn(name = "id_prioridade", referencedColumnName = "prioridade_id")},
            inverseJoinColumns = {@JoinColumn(name = "id_raca", referencedColumnName = "raca_id")}
    )
    private Set<Raca> racas;

    @ManyToMany
    @JoinTable(
            name = "genero_prioridade",
            joinColumns = {@JoinColumn(name = "id_prioridade", referencedColumnName = "prioridade_id")},
            inverseJoinColumns = {@JoinColumn(name = "id_genero", referencedColumnName = "genero_id")}
    )
    private Set<Genero> generos;
}
