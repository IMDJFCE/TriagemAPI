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
@SQLDelete(sql = "UPDATE oportunidade SET deleted_at = CURRENT_TIMESTAMP WHERE id=?")
@Where(clause = "deleted_at is null")
public class Oportunidade extends AbstractEntity{

    @NotBlank
    @Size(max = 100)
    private String titulo;

    @NotNull
    private LocalDate dataInicial;

    @NotNull
    private LocalDate dataFinal;

    @NotBlank
    private String descricao;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoTriagem triagem;

    @ManyToMany
    @JoinTable(
            name = "oportunidade_habilidade",
            joinColumns = {@JoinColumn(name = "id_oportunidade", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_habilidade", referencedColumnName = "habilidade_id")})
    @NotNull
    private Set<Habilidade> habilidades;

    @Email
    @NotBlank
    @Size(max = 70)
    private String email;

    @NotBlank
    private String informacoes;

    public enum TipoTriagem {
        CEGA,
        GENERO,
        RACA,
        DEFICIENCIA,
        NENHUMA
    }

    public void update(Oportunidade oportunidade){
        this.setTitulo(oportunidade.getTitulo());
        this.setDataInicial(oportunidade.getDataInicial());
        this.setDataFinal(oportunidade.getDataFinal());
        this.setDescricao(oportunidade.getDescricao());
        this.setTriagem(oportunidade.getTriagem());
        this.setEmail(oportunidade.getEmail());
        this.setInformacoes(oportunidade.getInformacoes());
    }
}
