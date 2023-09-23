package br.com.jfce.apibancotalentos.controller;

import br.com.jfce.apibancotalentos.dto.OportunidadeRequestDTO;
import br.com.jfce.apibancotalentos.dto.OportunidadeResponseDTO;
import br.com.jfce.apibancotalentos.model.Oportunidade;
import br.com.jfce.apibancotalentos.service.OportunidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/oportunidades/")
@AllArgsConstructor
@Tag(
        name = "Oportunidade Controller",
        description = "Controlador responsável por gerenciar operações relacionadas a oportunidades."
)
public class OportunidadeController {
    private OportunidadeService service;

    @GetMapping
    @Operation(
            summary = "Listar Todas as Oportunidades",
            description = "Este endpoint retorna uma lista de todas as oportunidades disponíveis."
    )
    public List<OportunidadeResponseDTO> listAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Obter uma Oportunidade por ID",
            description = "Este endpoint retorna uma oportunidade específica com base no ID fornecido."
    )
    public OportunidadeResponseDTO getById(@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping
    @Operation(
            summary = "Criar uma Nova Oportunidade",
            description = "Este endpoint permite criar uma nova oportunidade."
    )
    public OportunidadeResponseDTO create(@RequestBody OportunidadeRequestDTO oportunidade){
        return service.create(oportunidade);
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Atualizar uma Oportunidade Existente",
            description = "Este endpoint permite atualizar os dados de uma oportunidade existente."
    )
    public OportunidadeResponseDTO update(@PathVariable String id, @RequestBody OportunidadeRequestDTO oportunidade){
        return service.update(id, oportunidade);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Excluir uma Oportunidade",
            description = "Este endpoint permite excluir uma oportunidade com base no ID fornecido."
    )
    public void delete(@PathVariable String id){
        service.delete(id);
    }
}
