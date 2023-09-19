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
public class Oportunidade extends AbstractEntity<Oportunidade>{
    private String titulo;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private String descricao;
    private String triagem;
    private String habilidades;
    private String email;
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
