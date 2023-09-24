package br.com.jfce.apibancotalentos.model;

import br.com.jfce.apibancotalentos.model.Enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE convite SET deleted_at = CURRENT_TIMESTAMP WHERE convite_id=?")
@Where(clause = "deleted_at is null")
public class Convite extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "convite_id")
    private String id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    private LocalDateTime dataEnvio;
}
