package br.com.jfce.apibancotalentos.service;

import br.com.jfce.apibancotalentos.dto.TriagemRequestDTO;
import br.com.jfce.apibancotalentos.dto.TriagemResponseDTO;
import br.com.jfce.apibancotalentos.dto.mapper.TriagemMapper;
import br.com.jfce.apibancotalentos.model.Triagem;
import br.com.jfce.apibancotalentos.repository.TriagemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TriagemService {
    private final TriagemRepository triagemRepository;
    private final TriagemMapper triagemMapper;

    public TriagemService(TriagemRepository triagemRepository, TriagemMapper triagemMapper) {
        this.triagemRepository = triagemRepository;
        this.triagemMapper = triagemMapper;
    }

    public List<TriagemResponseDTO> findAll(){
        return triagemRepository.findAll()
                .stream()
                .map(triagemMapper::toTriagemResponseDTO)
                .toList();
    }

    public TriagemResponseDTO getById(String id){
        Optional<Triagem> triagem = triagemRepository.findById(id);
        if(triagem.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        return triagemMapper.toTriagemResponseDTO(triagem.get());
    }

    public TriagemResponseDTO create(TriagemRequestDTO triagem){
        Triagem created = triagemRepository.save(triagemMapper.toTriagem(triagem));
        return triagemMapper.toTriagemResponseDTO(created);
    }

    public TriagemResponseDTO update(String id, TriagemRequestDTO triagemRequest){
        Optional<Triagem> triagemOptional = triagemRepository.findById(id);
        if(triagemOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        Triagem triagem = triagemMapper.toTriagem(triagemRequest);
        triagem.setId(id);
        triagem.setCreatedAt(triagemOptional.get().getCreatedAt());
        triagem.setDeletedAt(triagemOptional.get().getDeletedAt());
        triagem.setUpdatedAt(LocalDateTime.now());
        triagem = triagemRepository.save(triagem);
        return triagemMapper.toTriagemResponseDTO(triagem);
    }

    public void delete(String id){
        Optional<Triagem> triagem = triagemRepository.findById(id);

        if(triagem.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        triagemRepository.delete(triagem.get());
    }
}
