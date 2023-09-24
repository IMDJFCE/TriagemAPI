package br.com.jfce.apibancotalentos.repository;

import br.com.jfce.apibancotalentos.model.Oportunidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OportunidadeRepository extends JpaRepository<Oportunidade, String>, PagingAndSortingRepository<Oportunidade, String> {
}
