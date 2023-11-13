package br.com.jfce.apibancotalentos.repository;

import br.com.jfce.apibancotalentos.model.Deficiencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeficienciaRepository extends JpaRepository<Deficiencia, String> {
    Optional<Deficiencia> findByDescricao(String descricao);
}
