package br.com.jfce.apibancotalentos.repository;

import br.com.jfce.apibancotalentos.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PerfilRepository extends JpaRepository<Perfil, String>, PagingAndSortingRepository<Perfil, String> {
}
