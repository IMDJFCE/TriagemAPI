package br.com.jfce.apibancotalentos.dto;

import br.com.jfce.apibancotalentos.model.Habilidade;
import br.com.jfce.apibancotalentos.model.Oportunidade;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class OportunidadeResponseDTO {
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

    @Enumerated(EnumType.STRING)
    @NotNull
    private Oportunidade.TipoTriagem triagem;

    @NotNull
    private Set<Habilidade> habilidades;

    @NotBlank
    @Email
    @Size(max = 70)
    private String email;

    @NotBlank
    private String informacoes;
}
