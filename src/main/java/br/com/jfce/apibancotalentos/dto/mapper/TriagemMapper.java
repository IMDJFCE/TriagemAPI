package br.com.jfce.apibancotalentos.dto.mapper;

import br.com.jfce.apibancotalentos.dto.TriagemRequestDTO;
import br.com.jfce.apibancotalentos.dto.TriagemResponseDTO;
import br.com.jfce.apibancotalentos.model.Triagem;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TriagemMapper {
    private final ModelMapper mapper;

    public TriagemMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Triagem toTriagem(TriagemRequestDTO dto){
        return this.mapper.map(dto, Triagem.class);
    }

    public TriagemResponseDTO toTriagemResponseDTO(Triagem triagem){
        return this.mapper.map(triagem, TriagemResponseDTO.class);
    }
}
