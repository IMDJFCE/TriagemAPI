package br.com.jfce.apibancotalentos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Deficiencia {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "deficiencia_id")
    @JsonIgnore
    private String id;

    private String descricao;

    @ManyToMany(mappedBy = "deficiencias")
    @JsonIgnore
    private Set<Usuario> usuarios;
}
