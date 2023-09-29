package br.com.jfce.apibancotalentos.service;

import br.com.jfce.apibancotalentos.dto.PerfilRequestDTO;
import br.com.jfce.apibancotalentos.dto.PerfilResponseDTO;
import br.com.jfce.apibancotalentos.dto.mapper.PerfilMapper;
import br.com.jfce.apibancotalentos.model.Perfil;
import br.com.jfce.apibancotalentos.model.Usuario;
import br.com.jfce.apibancotalentos.repository.PerfilRepository;
import br.com.jfce.apibancotalentos.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public List<PerfilResponseDTO> findAll(Pageable page){
        Page<Perfil> perfilPage = perfilRepository.findAll(page);
        List<Perfil> perfilList = perfilPage.getContent();
        return perfilList.stream()
                .map(perfilMapper::toPerfilResponseDTO)
                .toList();
    }

    public PerfilResponseDTO getById(String id){
        Optional<Perfil> perfil = perfilRepository.findById(id);
        if(perfil.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        return perfilMapper.toPerfilResponseDTO(perfil.get());
    }

    public PerfilResponseDTO create(String usuarioId, PerfilRequestDTO perfil){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        if(usuarioOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        Perfil created = perfilMapper.toPerfil(perfil);
        created = perfilRepository.save(created);
        Usuario existingUsuario = usuarioOptional.get();
        existingUsuario.setPerfil(created);
        usuarioRepository.save(existingUsuario);
        return perfilMapper.toPerfilResponseDTO(created);
    }

    public PerfilResponseDTO update(String perfilId, PerfilRequestDTO perfilRequest){
        Optional<Perfil> perfilOptional = perfilRepository.findById(perfilId);
        if(perfilOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        Perfil existingPerfil = perfilOptional.get();
        existingPerfil.update(perfilMapper.toPerfil(perfilRequest));
        existingPerfil = perfilRepository.save(existingPerfil);
        return perfilMapper.toPerfilResponseDTO(existingPerfil);
    }

    public void delete(String perfilId){
        Optional<Perfil> perfil = perfilRepository.findById(perfilId);
        if(perfil.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        perfilRepository.delete(perfil.get());
    }
}
