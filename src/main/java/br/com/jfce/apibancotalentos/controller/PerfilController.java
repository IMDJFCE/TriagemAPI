package br.com.jfce.apibancotalentos.controller;

import br.com.jfce.apibancotalentos.model.Perfil;
import br.com.jfce.apibancotalentos.service.PerfilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfis/")
@AllArgsConstructor
@Tag(
        name = "Perfil Controller",
        description = "Controlador responsável por gerenciar operações relacionadas a perfis."
)
public class PerfilController {
    private PerfilService service;

    @GetMapping
    @Operation(
            summary = "Listar Todos os Perfis",
            description = "Este endpoint retorna uma lista de todos os perfis disponíveis"
    )
    public List<Perfil> listAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Obter um Perfil por ID",
            description = "Este endpoint retorna um perfil específico com base no ID fornecido."
    )
    public Perfil getById(@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping
    @Operation(
            summary = "Criar um Novo Perfil",
            description = "Este endpoint permite criar um novo perfil."
    )
    public void create(@RequestBody Perfil perfil){
        service.create(perfil);
    }

    @PutMapping
    @Operation(
            summary = "Atualizar um Perfil Existente",
            description = "Este endpoint permite atualizar os dados de um perfil existente."
    )
    public Perfil update(@RequestBody Perfil perfil){
        return service.update(perfil);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Excluir um Perfil",
            description = "Este endpoint permite excluir um perfil com base no ID fornecido."
    )
    public void delete(@PathVariable String id){
        service.delete(id);
    }
}
