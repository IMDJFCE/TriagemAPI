package br.com.jfce.apibancotalentos.controller;

import br.com.jfce.apibancotalentos.model.Perfil;
import br.com.jfce.apibancotalentos.service.PerfilService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("candidatos")
public class PerfilController {
    private PerfilService service;

    public PerfilController(PerfilService service) {
        this.service = service;
    }

    @GetMapping
    public List<Perfil> listAll(){
        return service.listAll();
    }

    @PostMapping
    public void create(@RequestBody Perfil perfil){
        service.create(perfil);
    }
}
