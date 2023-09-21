package br.com.jfce.apibancotalentos.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario extends AbstractEntity<Usuario>{
    private String nome;
    private String email;
    private String senha;
    private LocalDateTime dataNascimento;
    private String matricula;
    private String vinculo;


    @Override
    public void update(Usuario source) {
        this.nome = source.getNome();
        this.email = source.getEmail();
        this.senha = source.getSenha();
        this.dataNascimento = source.getDataNascimento();
        this.matricula = source.getMatricula();
        this.vinculo = source.getVinculo();
    }

    @Override
    public Usuario createNewInstance() {
        Usuario newInstance = new Usuario();
        newInstance.update(this);
        return newInstance;
    }
}
