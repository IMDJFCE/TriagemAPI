package br.com.jfce.apibancotalentos.repository;

import br.com.jfce.apibancotalentos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
