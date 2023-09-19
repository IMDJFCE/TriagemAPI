package br.com.jfce.apibancotalentos.controller;

import br.com.jfce.apibancotalentos.model.Oportunidade;
import br.com.jfce.apibancotalentos.service.OportunidadeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/oportunidades/")
@AllArgsConstructor
public class OportunidadeController {
    private OportunidadeService service;

    @GetMapping
    public List<Oportunidade> listAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
    public Oportunidade getById(@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping
    public void create(@RequestBody Oportunidade oportunidade){
        service.create(oportunidade);
    }

    @PutMapping
    public Oportunidade update(@RequestBody Oportunidade oportunidade){
        return service.update(oportunidade);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        service.delete(id);
    }
}
