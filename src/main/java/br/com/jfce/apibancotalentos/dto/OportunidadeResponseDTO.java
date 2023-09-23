package br.com.jfce.apibancotalentos.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OportunidadeResponseDTO {
    private String id;
    private String titulo;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private String descricao;
    private String triagem;
    private String habilidades;
    private String email;
    private String informacoes;
}
