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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE usuario SET deleted_at = CURRENT_TIMESTAMP WHERE id=?")
@Where(clause = "deleted_at is null")
public class Usuario extends AbstractEntity{

    @NotBlank
    @Size(max = 100)
    private String nome;

    @NotBlank
    @Email
    @Size(max = 70)
    private String email;

    @NotBlank
    @Size(min = 8, max = 50)
    private String senha;

    @NotNull
    private LocalDate dataNascimento;

    @NotBlank
    @Size(max = 50)
    private String matricula;

    @NotBlank
    @Size(max = 15)
    private String tipo;

    @ManyToMany
    @JoinTable(
            name = "usuario_habilidade",
            joinColumns = {@JoinColumn(name = "id_usuario", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_habilidade", referencedColumnName = "habilidade_id")}
    )
    private Set<Habilidade> habilidades;

    @ManyToMany
    @JoinTable(
            name = "usuario_deficiencia",
            joinColumns = {@JoinColumn(name = "id_usuario", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_deficiencia", referencedColumnName = "deficiencia_id")}
    )
    private Set<Deficiencia> deficiencias;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "raca_id")
    private Raca raca;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "genero_id")
    private Genero genero;

    public void update(Usuario usuario){
        this.setNome(usuario.getNome());
        this.setEmail(usuario.getEmail());
        this.setSenha(usuario.getSenha());
        this.setDataNascimento(usuario.getDataNascimento());
        this.setMatricula(usuario.getMatricula());
        this.setTipo(usuario.getTipo());
        this.setRaca(usuario.getRaca());
        this.setGenero(usuario.getGenero());
    }
}
