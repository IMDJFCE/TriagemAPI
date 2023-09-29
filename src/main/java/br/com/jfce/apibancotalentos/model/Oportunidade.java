package br.com.jfce.apibancotalentos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE oportunidade SET deleted_at = CURRENT_TIMESTAMP WHERE oportunidade_id=?")
@Where(clause = "deleted_at is null")
public class Oportunidade extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "oportunidade_id")
    private String id;

    @NotBlank
    @Size(max = 100)
    private String titulo;

    @NotNull
    private LocalDate dataInicial;

    @NotNull
    private LocalDate dataFinal;

    @NotBlank
    private String descricao;

    @NotBlank
    private String triagem;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "oportunidade_habilidade",
            joinColumns = {@JoinColumn(name = "id_oportunidade", referencedColumnName = "oportunidade_id")},
            inverseJoinColumns = {@JoinColumn(name = "id_habilidade", referencedColumnName = "habilidade_id")})
    private Set<Habilidade> habilidades;

    @Email
    @NotBlank
    @Size(max = 70)
    private String email;

    @NotBlank
    private String informacoes;
}
