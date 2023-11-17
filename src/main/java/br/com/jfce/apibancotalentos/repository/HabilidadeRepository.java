package br.com.jfce.apibancotalentos.repository;

import br.com.jfce.apibancotalentos.model.Habilidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HabilidadeRepository extends JpaRepository<Habilidade, Long> {
    Optional<Habilidade> findByNome(String nome);
}
