package br.com.jfce.apibancotalentos.controller;

import br.com.jfce.apibancotalentos.model.Oportunidade;
import br.com.jfce.apibancotalentos.service.OportunidadeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("oportunidades")
@AllArgsConstructor
public class OportunidadeController {
    private OportunidadeService service;

    @GetMapping
    public List<Oportunidade> listAll(){
        return service.findAll();
    }

    @PostMapping
    public void create(@RequestBody Oportunidade oportunidade){
        service.create(oportunidade);
    }
}
