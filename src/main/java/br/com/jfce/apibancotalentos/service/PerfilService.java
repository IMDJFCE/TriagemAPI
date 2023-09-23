package br.com.jfce.apibancotalentos.service;

import br.com.jfce.apibancotalentos.dto.PerfilRequestDTO;
import br.com.jfce.apibancotalentos.dto.PerfilResponseDTO;
import br.com.jfce.apibancotalentos.dto.mapper.PerfilMapper;
import br.com.jfce.apibancotalentos.model.Perfil;
import br.com.jfce.apibancotalentos.model.Usuario;
import br.com.jfce.apibancotalentos.repository.PerfilRepository;
import br.com.jfce.apibancotalentos.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PerfilService{
    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;
    private final PerfilMapper perfilMapper;

    public PerfilService(PerfilRepository perfilRepository, UsuarioRepository usuarioRepository, PerfilMapper perfilMapper) {
        this.perfilRepository = perfilRepository;
        this.usuarioRepository = usuarioRepository;
        this.perfilMapper = perfilMapper;
    }

    public List<PerfilResponseDTO> findAll(){
        return perfilRepository.findAll()
                .stream()
                .map(perfilMapper::toPerfilResponseDTO)
                .toList();
    }

    public PerfilResponseDTO getById(String id){
        Optional<Perfil> perfil = perfilRepository.findById(id);
        if(perfil.isEmpty()){
            throw new EntityNotFoundException("Not found");
        }

        return perfilMapper.toPerfilResponseDTO(perfil.get());
    }

    public PerfilResponseDTO create(String usuarioId, PerfilRequestDTO perfil){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        if(usuarioOptional.isEmpty()){
            throw new EntityNotFoundException("Usuario Not found");
        }

        Perfil created = perfilRepository.save(perfilMapper.toPerfil(perfil));
        Usuario existingUsuario = usuarioOptional.get();
        existingUsuario.setPerfil(created);
        usuarioRepository.save(existingUsuario);
        return perfilMapper.toPerfilResponseDTO(created);
    }

    public PerfilResponseDTO update(String perfilId, PerfilRequestDTO perfilRequest){
        Perfil perfil = perfilMapper.toPerfil(perfilRequest);
        Optional<Perfil> perfilOptional = perfilRepository.findById(perfilId);
        if(perfilOptional.isEmpty()){
            throw new EntityNotFoundException("Perfil Not found");
        }

        perfil.setId(perfilId);
        perfil.setCreatedAt(perfilOptional.get().getCreatedAt());
        perfil.setDeletedAt(perfilOptional.get().getDeletedAt());
        perfil.setUpdatedAt(LocalDateTime.now());
        perfil = perfilRepository.save(perfil);
        return perfilMapper.toPerfilResponseDTO(perfil);
    }

    public void delete(String perfilId){
        Optional<Perfil> perfil = perfilRepository.findById(perfilId);
        if(perfil.isEmpty()){
            throw new EntityNotFoundException("Perfil Not found");
        }

        perfil.get().setDeletedAt(LocalDateTime.now());
        perfilRepository.save(perfil.get());
    }
}
