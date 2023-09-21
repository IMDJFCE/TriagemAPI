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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Oportunidade extends AbstractEntity<Oportunidade>{
    @NotBlank(message = "O campo título não pode estar em branco.")
    @Size(max = 100, message = "O campo titulo deve ter no máximo 100 caracteres.")
    private String titulo;

    @NotNull(message = "O campo data inicial não pode ser nulo.")
    private LocalDate dataInicial;

    @NotNull(message = "O campo data final não pode ser nulo.")
    private LocalDate dataFinal;

    @NotBlank(message = "O campo descrição não pode estar em branco.")
    private String descricao;

    @NotBlank(message = "O campo triagem não pode estar em branco.")
    private String triagem;

    @NotBlank(message = "O campo habilidades não pode estar em branco.")
    private String habilidades;

    @Email(message = "Endereço de e-mail inválido.")
    @NotBlank(message = "O campo e-mail não pode estar em branco.")
    @Size(max = 70, message = "O campo e-mail deve ter no máximo 70 caracteres.")
    private String email;

    @NotBlank(message = "O campo informações não pode estar em branco.")
    private String informacoes;

    @Override
    public void update(Oportunidade source) {
        this.titulo = source.getTitulo();
        this.dataInicial = source.getDataInicial();
        this.dataFinal = source.getDataFinal();
        this.descricao = source.getDescricao();
        this.triagem = source.getTriagem();
        this.habilidades = source.getHabilidades();
        this.email = source.getEmail();
        this.informacoes = source.getInformacoes();
    }

    @Override
    public Oportunidade createNewInstance() {
        Oportunidade newInstance = new Oportunidade();
        newInstance.update(this);
        return newInstance;
    }
}
