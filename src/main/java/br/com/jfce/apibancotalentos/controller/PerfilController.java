package br.com.jfce.apibancotalentos.controller;

import br.com.jfce.apibancotalentos.dto.PerfilRequestDTO;
import br.com.jfce.apibancotalentos.dto.PerfilResponseDTO;
import br.com.jfce.apibancotalentos.service.PerfilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
@Tag(
        name = "Perfil Controller",
        description = "Controlador responsável por gerenciar operações relacionadas a perfis."
)
public class PerfilController {
    private PerfilService service;

    /*@GetMapping("usuarios/perfis/")
    @Operation(
            summary = "Listar Todos os Perfis",
            description = "Este endpoint retorna uma lista de todos os perfis disponíveis"
    )
    @ResponseStatus(HttpStatus.OK)
    public List<PerfilResponseDTO> listAll(){
        return service.findAll();
    }*/

    @GetMapping("usuarios/perfis/")
    @Operation(
            summary = "Listar Todos os Perfis",
            description = "Este endpoint retorna uma lista de todos os perfis disponíveis"
                        + "Ele suporta paginação e ordenação dos resultados através dos parâmetros 'page' e 'sort'. "
                        + "Os resultados são retornados em formato JSON com informações detalhadas sobre cada perfil."
    )
    @ResponseStatus(HttpStatus.OK)
    public List<PerfilResponseDTO> listAll(Pageable page){
        return service.findAll(page);
    }

    @GetMapping("usuarios/perfis/{id}")
    @Operation(
            summary = "Obter um Perfil por ID",
            description = "Este endpoint retorna um perfil específico com base no ID fornecido."
    )
    @ResponseStatus(HttpStatus.OK)
    public PerfilResponseDTO getById(@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping("usuarios/{usuarioId}/perfis/")
    @Operation(
            summary = "Criar um Novo Perfil",
            description = "Este endpoint permite criar um novo perfil."
    )
    @ResponseStatus(HttpStatus.CREATED)
    public PerfilResponseDTO create(@PathVariable String usuarioId, @Valid @RequestBody PerfilRequestDTO perfil){
        return service.create(usuarioId, perfil);
    }

    @PutMapping("usuarios/perfis/{perfilId}")
    @Operation(
            summary = "Atualizar um Perfil Existente",
            description = "Este endpoint permite atualizar os dados de um perfil existente."
    )
    @ResponseStatus(HttpStatus.OK)
    public PerfilResponseDTO update(@PathVariable String perfilId, @Valid @RequestBody PerfilRequestDTO perfil){
        return service.update(perfilId, perfil);
    }

    @DeleteMapping("usuarios/perfis/{perfilId}")
    @Operation(
            summary = "Excluir um Perfil",
            description = "Este endpoint permite excluir um perfil com base no ID fornecido."
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String perfilId){
        service.delete(perfilId);
    }
}
