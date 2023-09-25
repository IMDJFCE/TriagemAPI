package br.com.jfce.apibancotalentos.service;

import br.com.jfce.apibancotalentos.dto.OportunidadeRequestDTO;
import br.com.jfce.apibancotalentos.dto.OportunidadeResponseDTO;
import br.com.jfce.apibancotalentos.dto.mapper.OportunidadeMapper;
import br.com.jfce.apibancotalentos.model.Habilidade;
import br.com.jfce.apibancotalentos.model.Oportunidade;
import br.com.jfce.apibancotalentos.repository.HabilidadeRepository;
import br.com.jfce.apibancotalentos.repository.OportunidadeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OportunidadeService{
    private final OportunidadeRepository oportunidadeRepository;
    private final HabilidadeRepository habilidadeRepository;
    private final OportunidadeMapper oportunidadeMapper;

    public OportunidadeService(OportunidadeRepository oportunidadeRepository, HabilidadeRepository habilidadeRepository, OportunidadeMapper oportunidadeMapper) {
        this.oportunidadeRepository = oportunidadeRepository;
        this.habilidadeRepository = habilidadeRepository;
        this.oportunidadeMapper = oportunidadeMapper;
    }

    public List<OportunidadeResponseDTO> findAll(){
        return oportunidadeRepository.findAll()
                .stream()
                .map(oportunidadeMapper::toOportunidadeResponseDTO)
                .toList();
    }

    public List<OportunidadeResponseDTO> findAll(Pageable page){
        Page<Oportunidade> oportunidadePage = oportunidadeRepository.findAll(page);
        List<Oportunidade> oportunidadeList = oportunidadePage.getContent();
        return oportunidadeList.stream()
                .map(oportunidadeMapper::toOportunidadeResponseDTO)
                .toList();
    }

    public OportunidadeResponseDTO getById(String id){
        Optional<Oportunidade> oportunidade = oportunidadeRepository.findById(id);
        if(oportunidade.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        return oportunidadeMapper.toOportunidadeResponseDTO(oportunidade.get());
    }

    public OportunidadeResponseDTO create(OportunidadeRequestDTO oportunidade){
        Oportunidade created = oportunidadeMapper.toOportunidade(oportunidade);
        Set<Habilidade> habilidades = new HashSet<>();
        for(Habilidade habilidade : created.getHabilidades()){
            habilidade = habilidadeRepository.save(habilidade);
            habilidades.add(habilidade);
        }
        created.setHabilidades(habilidades);
        created = oportunidadeRepository.save(created);
        return oportunidadeMapper.toOportunidadeResponseDTO(created);
    }

    public OportunidadeResponseDTO update(String id, OportunidadeRequestDTO oportunidadeRequest){
        Optional<Oportunidade> oportunidadeOptional = oportunidadeRepository.findById(id);
        if(oportunidadeOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        Oportunidade oportunidade = oportunidadeMapper.toOportunidade(oportunidadeRequest);
        Set<Habilidade> habilidades = new HashSet<>();
        for(Habilidade habilidade : oportunidade.getHabilidades()){
            habilidade = habilidadeRepository.save(habilidade);
            habilidades.add(habilidade);
        }

        oportunidade.setHabilidades(habilidades);
        oportunidade.setId(id);
        oportunidade.setCreatedAt(oportunidadeOptional.get().getCreatedAt());
        oportunidade.setDeletedAt(oportunidadeOptional.get().getDeletedAt());
        oportunidade.setUpdatedAt(LocalDateTime.now());
        oportunidade = oportunidadeRepository.save(oportunidade);
        return oportunidadeMapper.toOportunidadeResponseDTO(oportunidade);
    }

    public void delete(String id){
        Optional<Oportunidade> oportunidade = oportunidadeRepository.findById(id);
        if(oportunidade.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        oportunidadeRepository.delete(oportunidade.get());
    }
}
