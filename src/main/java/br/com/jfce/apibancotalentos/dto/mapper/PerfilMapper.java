package br.com.jfce.apibancotalentos.dto.mapper;

import br.com.jfce.apibancotalentos.dto.PerfilRequestDTO;
import br.com.jfce.apibancotalentos.dto.PerfilResponseDTO;
import br.com.jfce.apibancotalentos.model.Perfil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PerfilMapper {
    private ModelMapper mapper;

    public PerfilMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Perfil toPerfil(PerfilRequestDTO dto){
        return this.mapper.map(dto, Perfil.class);
    }

    public PerfilResponseDTO toPerfilResponseDTO(Perfil perfil){
        return this.mapper.map(perfil, PerfilResponseDTO.class);
    }
}
