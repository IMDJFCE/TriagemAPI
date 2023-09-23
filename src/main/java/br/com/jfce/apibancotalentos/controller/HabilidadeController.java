package br.com.jfce.apibancotalentos.controller;

import br.com.jfce.apibancotalentos.dto.HabilidadeRequestDTO;
import br.com.jfce.apibancotalentos.dto.HabilidadeResponseDTO;
import br.com.jfce.apibancotalentos.service.HabilidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habilidades/")
@AllArgsConstructor
@Tag(
        name = "Habilidade Controller",
        description = "Controlador responsável por gerenciar operações relacionadas a habilidade."
)
public class HabilidadeController {
    private final HabilidadeService service;

    @GetMapping
    @Operation(
            summary = "Listar Todas as Habilidades",
            description = "Este endpoint retorna uma lista de todas as habilidades existentes."
    )
    public List<HabilidadeResponseDTO> listAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Obter uma Habilidade por ID",
            description = "Este endpoint retorna uma habilidade específica com base no ID fornecido."
    )
    public HabilidadeResponseDTO getById(@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping
    @Operation(
            summary = "Criar uma Nova Habilidade",
            description = "Este endpoint permite criar uma nova habilidade."
    )
    public HabilidadeResponseDTO create(@RequestBody HabilidadeRequestDTO habilidade){
        return service.create(habilidade);
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Atualizar uma Habilidade Existente",
            description = "Este endpoint permite atualizar os dados de uma habilidade existente."
    )
    public HabilidadeResponseDTO update(@PathVariable String id, @RequestBody HabilidadeRequestDTO habilidadeRequest){
        return service.update(id, habilidadeRequest);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Excluir uma Habilidade",
            description = "Este endpoint permite excluir uma habilidade com base no ID fornecido."
    )
    public void delete(@PathVariable String id){
        service.delete(id);
    }
}
