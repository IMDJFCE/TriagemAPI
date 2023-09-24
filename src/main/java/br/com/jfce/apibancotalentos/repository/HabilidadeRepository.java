package br.com.jfce.apibancotalentos.repository;

import br.com.jfce.apibancotalentos.model.Habilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HabilidadeRepository extends JpaRepository<Habilidade, String>, PagingAndSortingRepository<Habilidade, String> {
}
