package br.com.jfce.apibancotalentos.service;

import br.com.jfce.apibancotalentos.dto.UsuarioRequestDTO;
import br.com.jfce.apibancotalentos.dto.UsuarioResponseDTO;
import br.com.jfce.apibancotalentos.dto.mapper.UsuarioMapper;
import br.com.jfce.apibancotalentos.model.Usuario;
import br.com.jfce.apibancotalentos.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService{
    private final UsuarioRepository repository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository repository, UsuarioMapper usuarioMapper) {
        this.repository = repository;
        this.usuarioMapper = usuarioMapper;
    }

    public List<UsuarioResponseDTO> findAll(){
        return repository.findAll()
                .stream()
                .map(usuarioMapper::toUsuarioResponseDTO)
                .toList();
    }

    public UsuarioResponseDTO getById(String id){
        Optional<Usuario> usuario = repository.findById(id);
        if(usuario.isEmpty()){
            throw new EntityNotFoundException("Not found");
        }

        return usuarioMapper.toUsuarioResponseDTO(usuario.get());
    }

    public UsuarioResponseDTO create(UsuarioRequestDTO usuario){
        Usuario created = repository.save(usuarioMapper.toUsuario(usuario));
        return usuarioMapper.toUsuarioResponseDTO(created);
    }

    public UsuarioResponseDTO update(String id, UsuarioRequestDTO usuarioRequest){
        Usuario usuario = usuarioMapper.toUsuario(usuarioRequest);
        Optional<Usuario> usuarioOptional = repository.findById(id);
        if(usuarioOptional.isEmpty()) {
            throw new EntityNotFoundException("Not found");
        }

        usuario.setId(id);
        usuario.setCreatedAt(usuarioOptional.get().getCreatedAt());
        usuario.setDeletedAt(usuarioOptional.get().getDeletedAt());
        usuario.setUpdatedAt(LocalDateTime.now());
        usuario.setSenha(usuarioOptional.get().getSenha());
        usuario = repository.save(usuario);
        return usuarioMapper.toUsuarioResponseDTO(usuario);
    }

    public void delete(String id){
        Optional<Usuario> usuario = repository.findById(id);

        if(usuario.isEmpty()){
            throw new EntityNotFoundException("Not found");
        }

        usuario.get().setDeletedAt(LocalDateTime.now());
        repository.save(usuario.get());
    }
}
