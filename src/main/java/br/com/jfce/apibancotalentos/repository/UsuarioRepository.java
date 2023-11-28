package br.com.jfce.apibancotalentos.repository;

import br.com.jfce.apibancotalentos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
    Optional<Usuario> findByEmail(String email);
}
