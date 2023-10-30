package br.com.jfce.apibancotalentos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Deficiencia {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "deficiencia_id")
    private UUID id;

    private String descricao;
}
