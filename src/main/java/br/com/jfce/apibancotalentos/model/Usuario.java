package br.com.jfce.apibancotalentos.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario extends AbstractEntity<Usuario>{

    @NotBlank(message = "O campo nome não pode estar em branco.")
    @Size(max = 100, message = "O campo nome deve ter no máximo 100 caracteres.")
    private String nome;

    @NotBlank(message = "O campo email não pode estar em branco.")
    @Email(message = "O e-mail deve ser um endereço de e-mail válido.")
    @Size(max = 70, message = "O campo e-mail deve ter no máximo 70 caracteres.")
    private String email;

    @NotBlank(message = "O campo senha não pode estar em branco.")
    @Size(min = 8, max = 50, message = "O campo senha deve ter no mínimo 8 caracteres e no máximo 50 caracteres.")
    private String senha;

    @NotNull(message = "O campo data de nascimento não pode ser nulo.")
    private LocalDate dataNascimento;

    @NotBlank(message = "O campo matrícula não pode estar em branco.")
    @Size(max = 50, message = "O campo matrícula deve ter no máximo 50 caracteres.")
    private String matricula;

    @NotBlank(message = "O campo vínculo não pode estar em branco.")
    @Size(max = 15, message = "O campo vínculo deve ter no máximo 15 caracteres.")
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
