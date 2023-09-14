package br.com.jfce.apibancotalentos.service;

import br.com.jfce.apibancotalentos.model.AbstractEntity;
import br.com.jfce.apibancotalentos.repository.GenericRepository;

import java.util.List;

public abstract class AbstractService<E extends AbstractEntity, R extends GenericRepository<E>> implements IGenericService<E>{
    protected final R repository;

    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public List<E> findAll(){
        return repository.findAll();
    }

    @Override
    public E create(E entity)    {
        return  repository.save(entity);
    }
}
