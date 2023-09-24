package br.com.jfce.apibancotalentos.service;

import br.com.jfce.apibancotalentos.dto.OportunidadeRequestDTO;
import br.com.jfce.apibancotalentos.dto.OportunidadeResponseDTO;
import br.com.jfce.apibancotalentos.dto.mapper.OportunidadeMapper;
import br.com.jfce.apibancotalentos.model.Oportunidade;
import br.com.jfce.apibancotalentos.repository.OportunidadeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OportunidadeService{
    private final OportunidadeRepository repository;
    private final OportunidadeMapper oportunidadeMapper;

    public OportunidadeService(OportunidadeRepository repository, OportunidadeMapper oportunidadeMapper) {
        this.repository = repository;
        this.oportunidadeMapper = oportunidadeMapper;
    }

    public List<OportunidadeResponseDTO> findAll(){
        return repository.findAll()
                .stream()
                .map(oportunidadeMapper::toOportunidadeResponseDTO)
                .toList();
    }

    public OportunidadeResponseDTO getById(String id){
        Optional<Oportunidade> oportunidade = repository.findById(id);
        if(oportunidade.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        return oportunidadeMapper.toOportunidadeResponseDTO(oportunidade.get());
    }

    public OportunidadeResponseDTO create(OportunidadeRequestDTO oportunidade){
        Oportunidade created = repository.save(oportunidadeMapper.toOportunidade(oportunidade));
        return oportunidadeMapper.toOportunidadeResponseDTO(created);
    }

    public OportunidadeResponseDTO update(String id, OportunidadeRequestDTO oportunidadeRequest){
        Optional<Oportunidade> oportunidadeOptional = repository.findById(id);
        if(oportunidadeOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        Oportunidade oportunidade = oportunidadeMapper.toOportunidade(oportunidadeRequest);
        oportunidade.setId(id);
        oportunidade.setCreatedAt(oportunidadeOptional.get().getCreatedAt());
        oportunidade.setDeletedAt(oportunidadeOptional.get().getDeletedAt());
        oportunidade.setUpdatedAt(LocalDateTime.now());
        oportunidade = repository.save(oportunidade);
        return oportunidadeMapper.toOportunidadeResponseDTO(oportunidade);
    }

    public void delete(String id){
        Optional<Oportunidade> oportunidade = repository.findById(id);
        if(oportunidade.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        oportunidade.get().setDeletedAt(LocalDateTime.now());
        repository.save(oportunidade.get());
    }
}
