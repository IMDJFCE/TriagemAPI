package br.com.jfce.apibancotalentos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE habilidade SET deleted_at = CURRENT_TIMESTAMP WHERE habilidade_id=?")
@Where(clause = "deleted_at is null")
public class Habilidade extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "habilidade_id")
    private String id;

    @NotBlank
    @Size(max = 50)
    private String habilidade;

    @NotBlank
    @Size(max = 2)
    private String peso;
}
