package br.com.jfce.apibancotalentos.service;

import br.com.jfce.apibancotalentos.model.Usuario;
import br.com.jfce.apibancotalentos.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService{
    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario getById(String id){
        Optional<Usuario> usuario = repository.findById(id);
        if(usuario.isPresent()){
            return usuario.get();
        }else{
            throw new EntityNotFoundException("Not found");
        }
    }

    public Usuario create(Usuario usuario){
        return repository.save(usuario);
    }

    public Usuario update(String id, Usuario usuario){
        Optional<Usuario> usuarioOptional = repository.findById(id);
        if(usuarioOptional.isPresent()){
            usuario.setId(id);
            usuario.setCreatedAt(usuarioOptional.get().getCreatedAt());
            usuario.setDeletedAt(usuarioOptional.get().getDeletedAt());
            usuario.setUpdatedAt(LocalDateTime.now());
            return repository.save(usuario);
        }else{
            throw new EntityNotFoundException("Not found");
        }
    }

    public void delete(String id){
        Optional<Usuario> usuario = repository.findById(id);
        if(usuario.isPresent()){
            usuario.get().setDeletedAt(LocalDateTime.now());
            repository.save(usuario.get());
        }else{
            throw new EntityNotFoundException("Not found");
        }
    }
}
