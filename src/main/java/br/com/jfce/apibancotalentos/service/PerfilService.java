package br.com.jfce.apibancotalentos.service;

import br.com.jfce.apibancotalentos.model.Perfil;
import br.com.jfce.apibancotalentos.repository.PerfilRepository;
import org.springframework.stereotype.Service;

@Service
public class PerfilService extends AbstractService<Perfil, PerfilRepository>{
    public PerfilService(PerfilRepository repository) {
        super(repository);
    }
}
