package br.com.jfce.apibancotalentos.repository;

import br.com.jfce.apibancotalentos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String>, PagingAndSortingRepository<Usuario, String> {
}
