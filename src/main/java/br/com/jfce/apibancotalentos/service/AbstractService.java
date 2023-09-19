package br.com.jfce.apibancotalentos.service;

import br.com.jfce.apibancotalentos.model.AbstractEntity;
import br.com.jfce.apibancotalentos.repository.GenericRepository;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        return repository.save(entity);
    }

    @Override
    public E update(E entity){
        Optional<E> entityOptional = repository.findById(entity.getId());
        if(entityOptional.isPresent()){
            E existingEntity = entityOptional.get();
            existingEntity.setUpdatedAt(LocalDateTime.now());
            existingEntity.update(entity);
            return repository.save((existingEntity));
        }else{
            throw new EntityNotFoundException("Not found");
        }
    }

    @Override
    public E getById(String id){
        Optional<E> entity = repository.findById(id);
        if(entity.isPresent()){
            return entity.get();
        }else{
            throw new EntityNotFoundException("Not found");
        }
    }

    @Override
    public void delete(String id){
        Optional<E> entity = repository.findById(id);
        if(entity.isPresent()){
            E updateEntity = entity.get();
            updateEntity.setDeletedAt(LocalDateTime.now());
            repository.save(updateEntity);
        }else{
            throw new EntityNotFoundException("Not found");
        }
    }
}
