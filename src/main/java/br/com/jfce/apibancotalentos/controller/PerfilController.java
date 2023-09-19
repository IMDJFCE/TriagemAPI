package br.com.jfce.apibancotalentos.controller;

import br.com.jfce.apibancotalentos.model.Perfil;
import br.com.jfce.apibancotalentos.service.PerfilService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfis/")
@AllArgsConstructor
public class PerfilController {
    private PerfilService service;

    @GetMapping
    public List<Perfil> listAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
    public Perfil getById(@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping
    public void create(@RequestBody Perfil perfil){
        service.create(perfil);
    }

    @PutMapping
    public Perfil update(@RequestBody Perfil perfil){
        return service.update(perfil);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        service.delete(id);
    }
}
