package br.com.jfce.apibancotalentos.service;

import br.com.jfce.apibancotalentos.model.Oportunidade;
import br.com.jfce.apibancotalentos.repository.OportunidadeRepository;
import org.springframework.stereotype.Service;

@Service
public class OportunidadeService extends AbstractService<Oportunidade, OportunidadeRepository>{
    public OportunidadeService(OportunidadeRepository repository) {
        super(repository);
    }
}
