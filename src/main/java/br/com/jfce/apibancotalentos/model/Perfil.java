package br.com.jfce.apibancotalentos.model;

import br.com.jfce.apibancotalentos.model.Enums.Genero;
import br.com.jfce.apibancotalentos.model.Enums.Raca;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE perfil SET deleted_at = CURRENT_TIMESTAMP WHERE perfil_id=?")
@Where(clause = "deleted_at is null")
public class Perfil extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "perfil_id")
    private String id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Genero genero;

    @NotNull
    private boolean isDeficiente;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Raca raca;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "perfil_habilidade",
            joinColumns = {@JoinColumn(name = "id_perfil", referencedColumnName = "perfil_id")},
            inverseJoinColumns = {@JoinColumn(name = "id_habilidade", referencedColumnName = "habilidade_id")}
    )
    private Set<Habilidade> habilidades;

    public void update(Perfil perfil){
        this.setGenero(perfil.getGenero());
        this.setDeficiente(perfil.isDeficiente());
        this.setRaca(perfil.getRaca());
        this.setHabilidades(perfil.getHabilidades());
    }
}
