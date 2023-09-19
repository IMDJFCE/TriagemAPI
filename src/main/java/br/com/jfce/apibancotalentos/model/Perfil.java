package br.com.jfce.apibancotalentos.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Perfil extends AbstractEntity<Perfil>{

    private String nome;
    private LocalDate dataNascimento;
    private Genero genero;
    private boolean isDeficiente;
    private String etinia;
    private String habilidades;

    @Override
    public void update(Perfil source) {
        this.nome = source.getNome();
        this.dataNascimento = source.getDataNascimento();
        this.genero = source.getGenero();
        this.isDeficiente = source.isDeficiente();
        this.etinia = source.getEtinia();
        this.habilidades = source.getHabilidades();
    }

    @Override
    public Perfil createNewInstance() {
        Perfil newInstance = new Perfil();
        newInstance.update(this);
        return newInstance;
    }
}
