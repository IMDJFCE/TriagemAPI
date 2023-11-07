package br.com.jfce.apibancotalentos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class Convite extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "convite_id")
    private String id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    private LocalDateTime dataEnvio;

    public enum Status {
        PENDENTE("Pendente"),
        ENVIADO("Enviado");

        private final String status;

        Status(String status) {
            this.status = status;
        }
    }
}
