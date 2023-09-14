package br.com.jfce.apibancotalentos.service;

import br.com.jfce.apibancotalentos.model.AbstractEntity;

import java.util.List;

public interface IGenericService<E extends AbstractEntity> {
    List<E> findAll();
    E create(E entity);
}
