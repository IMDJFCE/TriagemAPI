package br.com.jfce.apibancotalentos.repository;

import br.com.jfce.apibancotalentos.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<E extends AbstractEntity> extends JpaRepository<E, String> {
}
