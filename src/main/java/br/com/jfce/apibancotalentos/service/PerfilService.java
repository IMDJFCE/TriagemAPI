package br.com.jfce.apibancotalentos.service;

import br.com.jfce.apibancotalentos.model.Perfil;
import br.com.jfce.apibancotalentos.repository.PerfilRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PerfilService{
    private PerfilRepository repository;

    public PerfilService(PerfilRepository repository) {
        this.repository = repository;
    }

    public List<Perfil> findAll(){
        return repository.findAll();
    }

    public Perfil getById(String id){
        Optional<Perfil> perfil = repository.findById(id);
        if(perfil.isPresent()){
            return perfil.get();
        }else{
            throw new EntityNotFoundException("Not found");
        }
    }

    public Perfil create(Perfil perfil){
        return repository.save(perfil);
    }

    public Perfil update(String id, Perfil perfil){
        Optional<Perfil> perfilOptional = repository.findById(id);
        if(perfilOptional.isPresent()){
            perfil.setId(id);
            perfil.setCreatedAt(perfilOptional.get().getCreatedAt());
            perfil.setDeletedAt(perfilOptional.get().getDeletedAt());
            perfil.setUpdatedAt(LocalDateTime.now());
            return repository.save(perfil);
        }else{
            throw new EntityNotFoundException("Not found");
        }
    }

    public void delete(String id){
        Optional<Perfil> perfil = repository.findById(id);
        if(perfil.isPresent()){
            perfil.get().setDeletedAt(LocalDateTime.now());
            repository.save(perfil.get());
        }else{
            throw new EntityNotFoundException("Not found");
        }
    }
}
