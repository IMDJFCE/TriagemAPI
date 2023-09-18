package br.com.jfce.apibancotalentos.service;

import br.com.jfce.apibancotalentos.model.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface IGenericService<E extends AbstractEntity> {
    List<E> findAll();
    E create(E entity);
    E update(E entity);
    E getById(String id);
    void delete(String id);
}
