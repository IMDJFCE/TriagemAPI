package br.com.jfce.apibancotalentos.controller;

import br.com.jfce.apibancotalentos.dto.TriagemRequestDTO;
import br.com.jfce.apibancotalentos.dto.TriagemResponseDTO;
import br.com.jfce.apibancotalentos.service.TriagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/triagens")
@AllArgsConstructor
@Tag(
        name = "Triagem Controller",
        description = "Controlador responsável por gerenciar operações relacionadas a triagem."
)
public class TriagemController {
    private final TriagemService service;

    @GetMapping
    @Operation(
            summary = "Listar Todas as Triagens",
            description = "Este endpoint retorna uma lista de todas as triagens existentes."
    )
    @ResponseStatus(HttpStatus.OK)
    public List<TriagemResponseDTO> listAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Obter uma Triagem por ID",
            description = "Este endpoint retorna uma triagem específica com base no ID fornecido."
    )
    @ResponseStatus(HttpStatus.OK)
    public TriagemResponseDTO getById(@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping
    @Operation(
            summary = "Criar uma Nova Triagem",
            description = "Este endpoint permite criar uma nova triagem."
    )
    @ResponseStatus(HttpStatus.CREATED)
    public TriagemResponseDTO create(@RequestBody TriagemRequestDTO triagem){
        return service.create(triagem);
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Atualizar uma Triagem Existente",
            description = "Este endpoint permite atualizar os dados de uma triagem existente."
    )
    @ResponseStatus(HttpStatus.OK)
    public TriagemResponseDTO update(@PathVariable String id, @RequestBody TriagemRequestDTO triagemRequest){
        return service.update(id, triagemRequest);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Excluir uma Triagem",
            description = "Este endpoint permite excluir uma triagem com base no ID fornecido."
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        service.delete(id);
    }
}
