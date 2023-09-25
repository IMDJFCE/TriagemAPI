package br.com.jfce.apibancotalentos.service;

import br.com.jfce.apibancotalentos.dto.HabilidadeRequestDTO;
import br.com.jfce.apibancotalentos.dto.HabilidadeResponseDTO;
import br.com.jfce.apibancotalentos.dto.mapper.HabilidadeMapper;
import br.com.jfce.apibancotalentos.model.Habilidade;
import br.com.jfce.apibancotalentos.repository.HabilidadeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class HabilidadeService {
    private final HabilidadeRepository habilidadeRepository;
    private final HabilidadeMapper habilidadeMapper;

    public HabilidadeService(HabilidadeRepository habilidadeRepository, HabilidadeMapper habilidadeMapper) {
        this.habilidadeRepository = habilidadeRepository;
        this.habilidadeMapper = habilidadeMapper;
    }

    public List<HabilidadeResponseDTO> findAll(){
        return habilidadeRepository.findAll()
                .stream()
                .map(habilidadeMapper::toHabilidadeResponseDTO)
                .toList();
    }

    public List<HabilidadeResponseDTO> findAll(Pageable page){
        Page<Habilidade> habilidadePage = habilidadeRepository.findAll(page);
        List<Habilidade> habilidadeList = habilidadePage.getContent();
        return habilidadeList.stream()
                .map(habilidadeMapper::toHabilidadeResponseDTO)
                .toList();
    }

    public HabilidadeResponseDTO getById(String id){
        Optional<Habilidade> habilidade = habilidadeRepository.findById(id);
        if(habilidade.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        return habilidadeMapper.toHabilidadeResponseDTO(habilidade.get());
    }

    public HabilidadeResponseDTO create(HabilidadeRequestDTO habilidade){
        Habilidade created = habilidadeRepository.save(habilidadeMapper.toHabilidade(habilidade));
        return habilidadeMapper.toHabilidadeResponseDTO(created);
    }

    public HabilidadeResponseDTO update(String id, HabilidadeRequestDTO habilidadeRequest){
        Optional<Habilidade> habilidadeOptional = habilidadeRepository.findById(id);
        if(habilidadeOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        Habilidade habilidade = habilidadeMapper.toHabilidade(habilidadeRequest);
        habilidade.setId(id);
        habilidade = habilidadeRepository.save(habilidade);
        return habilidadeMapper.toHabilidadeResponseDTO(habilidade);
    }

    public void delete(String id){
        Optional<Habilidade> habilidade = habilidadeRepository.findById(id);

        if(habilidade.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        habilidadeRepository.delete(habilidade.get());
    }
}
