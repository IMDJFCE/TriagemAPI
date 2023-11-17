package br.com.jfce.apibancotalentos.repository;

import br.com.jfce.apibancotalentos.model.Habilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface HabilidadeRepository extends JpaRepository<Habilidade, Long>, PagingAndSortingRepository<Habilidade, Long> {
    Optional<Habilidade> findByNome(String nome);
}
