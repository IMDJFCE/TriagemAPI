package br.com.jfce.apibancotalentos.service;

import br.com.jfce.apibancotalentos.dto.PerfilRequestDTO;
import br.com.jfce.apibancotalentos.dto.PerfilResponseDTO;
import br.com.jfce.apibancotalentos.dto.mapper.PerfilMapper;
import br.com.jfce.apibancotalentos.model.Habilidade;
import br.com.jfce.apibancotalentos.model.Perfil;
import br.com.jfce.apibancotalentos.model.Usuario;
import br.com.jfce.apibancotalentos.repository.HabilidadeRepository;
import br.com.jfce.apibancotalentos.repository.PerfilRepository;
import br.com.jfce.apibancotalentos.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PerfilService{
    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;

    private final HabilidadeRepository habilidadeRepository;
    private final PerfilMapper perfilMapper;

    public PerfilService(PerfilRepository perfilRepository, UsuarioRepository usuarioRepository, HabilidadeRepository habilidadeRepository, PerfilMapper perfilMapper) {
        this.perfilRepository = perfilRepository;
        this.usuarioRepository = usuarioRepository;
        this.habilidadeRepository = habilidadeRepository;
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

    @Transactional
    public PerfilResponseDTO create(String usuarioId, PerfilRequestDTO perfil){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        if(usuarioOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        Perfil created = perfilMapper.toPerfil(perfil);
        Set<Habilidade> habilidades = new HashSet<>();
        for(Habilidade habilidade : created.getHabilidades()){
            habilidade = habilidadeRepository.save(habilidade);
            habilidades.add(habilidade);
        }
        created.setHabilidades(habilidades);
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

        Perfil perfil = perfilMapper.toPerfil(perfilRequest);
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        perfilRepository.delete(perfil.get());
    }
}
