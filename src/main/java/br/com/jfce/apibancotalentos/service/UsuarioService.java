package br.com.jfce.apibancotalentos.service;

import br.com.jfce.apibancotalentos.dto.UsuarioRequestDTO;
import br.com.jfce.apibancotalentos.dto.UsuarioResponseDTO;
import br.com.jfce.apibancotalentos.dto.mapper.UsuarioMapper;
import br.com.jfce.apibancotalentos.model.Usuario;
import br.com.jfce.apibancotalentos.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public List<UsuarioResponseDTO> findAll(Pageable page){
        Page<Usuario> usuarioPage = repository.findAll(page);
        List<Usuario> usuarioList = usuarioPage.getContent();
        return usuarioList.stream()
                .map(usuarioMapper::toUsuarioResponseDTO)
                .toList();
    }

    public UsuarioResponseDTO getById(String id){
        Optional<Usuario> usuario = repository.findById(id);
        if(usuario.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        return usuarioMapper.toUsuarioResponseDTO(usuario.get());
    }

    public UsuarioResponseDTO create(UsuarioRequestDTO usuario){
        Usuario created = repository.save(usuarioMapper.toUsuario(usuario));
        return usuarioMapper.toUsuarioResponseDTO(created);
    }

    public UsuarioResponseDTO update(String id, UsuarioRequestDTO usuarioRequest){
        Optional<Usuario> usuarioOptional = repository.findById(id);
        if(usuarioOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        Usuario usuario = usuarioOptional.get();
        usuario.update(usuarioMapper.toUsuario(usuarioRequest));
        usuario = repository.save(usuario);
        return usuarioMapper.toUsuarioResponseDTO(usuario);
    }

    public void delete(String id){
        Optional<Usuario> usuario = repository.findById(id);

        if(usuario.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        repository.delete(usuario.get());
    }
}
