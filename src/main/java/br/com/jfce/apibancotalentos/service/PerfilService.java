package br.com.jfce.apibancotalentos.service;

import br.com.jfce.apibancotalentos.model.Perfil;
import br.com.jfce.apibancotalentos.repository.PerfilRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PerfilService {
    private PerfilRepository perfilRepository;

    public List<Perfil> listAll(){
        return perfilRepository.findAll();
    }

    public void create(Perfil perfil){
        perfilRepository.save(perfil);
    }
}
