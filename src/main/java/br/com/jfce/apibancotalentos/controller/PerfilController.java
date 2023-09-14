package br.com.jfce.apibancotalentos.controller;

import br.com.jfce.apibancotalentos.model.Perfil;
import br.com.jfce.apibancotalentos.service.PerfilService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("perfis")
@AllArgsConstructor
public class PerfilController {
    private PerfilService service;

    @GetMapping
    public List<Perfil> listAll(){
        return service.findAll();
    }

    @PostMapping
    public void create(@RequestBody Perfil perfil){
        service.create(perfil);
    }
}
