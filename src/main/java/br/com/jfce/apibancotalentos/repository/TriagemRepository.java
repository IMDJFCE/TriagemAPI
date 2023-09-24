package br.com.jfce.apibancotalentos.repository;

import br.com.jfce.apibancotalentos.model.Triagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TriagemRepository extends JpaRepository<Triagem, String>, PagingAndSortingRepository<Triagem, String> {
}
