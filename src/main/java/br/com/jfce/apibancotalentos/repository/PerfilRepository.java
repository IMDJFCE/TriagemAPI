package br.com.jfce.apibancotalentos.repository;

import br.com.jfce.apibancotalentos.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
