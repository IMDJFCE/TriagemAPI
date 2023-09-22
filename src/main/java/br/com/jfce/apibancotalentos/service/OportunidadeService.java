package br.com.jfce.apibancotalentos.service;

import br.com.jfce.apibancotalentos.model.Oportunidade;
import br.com.jfce.apibancotalentos.repository.OportunidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OportunidadeService{
    private OportunidadeRepository repository;

    public OportunidadeService(OportunidadeRepository repository) {
        this.repository = repository;
    }

    public List<Oportunidade> findAll(){
        return repository.findAll();
    }

    public Oportunidade getById(String id){
        Optional<Oportunidade> oportunidade = repository.findById(id);
        if(oportunidade.isPresent()){
            return oportunidade.get();
        }else{
            throw new EntityNotFoundException("Not found");
        }
    }

    public Oportunidade create(Oportunidade oportunidade){
        return repository.save(oportunidade);
    }

    public Oportunidade update(String id, Oportunidade oportunidade){
        Optional<Oportunidade> oportunidadeOptional = repository.findById(id);
        if(oportunidadeOptional.isPresent()){
            oportunidade.setId(id);
            oportunidade.setCreatedAt(oportunidadeOptional.get().getCreatedAt());
            oportunidade.setDeletedAt(oportunidadeOptional.get().getDeletedAt());
            oportunidade.setUpdatedAt(LocalDateTime.now());
            return repository.save(oportunidade);
        }else{
            throw new EntityNotFoundException("Not found");
        }
    }

    public void delete(String id){
        Optional<Oportunidade> oportunidade = repository.findById(id);
        if(oportunidade.isPresent()){
            oportunidade.get().setDeletedAt(LocalDateTime.now());
            repository.save(oportunidade.get());
        }else{
            throw new EntityNotFoundException("Not found");
        }
    }
}
