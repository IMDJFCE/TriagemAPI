package br.com.jfce.apibancotalentos.service;

import br.com.jfce.apibancotalentos.model.Usuario;
import br.com.jfce.apibancotalentos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends AbstractService<Usuario, UsuarioRepository>{
    public UsuarioService(UsuarioRepository repository) {
        super(repository);
    }
}
