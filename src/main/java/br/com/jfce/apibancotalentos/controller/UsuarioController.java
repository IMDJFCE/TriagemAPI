package br.com.jfce.apibancotalentos.controller;

import br.com.jfce.apibancotalentos.dto.UsuarioRequestDTO;
import br.com.jfce.apibancotalentos.dto.UsuarioResponseDTO;
import br.com.jfce.apibancotalentos.model.Usuario;
import br.com.jfce.apibancotalentos.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios/")
@AllArgsConstructor
@Tag(
        name = "Usuário Controller",
        description = "Controlador responsável por gerenciar operações relacionadas a usuários."
)
public class UsuarioController {
    private UsuarioService service;

    @GetMapping
    @Operation(
            summary = "Listar Todos os Usuários",
            description = "Este endpoint retorna uma lista de todos os usuários existentes."
    )
    public List<UsuarioResponseDTO> listAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Obter um Usuário por ID",
            description = "Este endpoint retorna um usuário específico com base no ID fornecido."
    )
    public UsuarioResponseDTO getById(@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping
    @Operation(
            summary = "Criar um Novo Usuário",
            description = "Este endpoint permite criar um novo usuário."
    )
    public UsuarioResponseDTO create(@RequestBody UsuarioRequestDTO usuario){
        return service.create(usuario);
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Atualizar um Usuário Existente",
            description = "Este endpoint permite atualizar os dados de um usuário existente."
    )
    public UsuarioResponseDTO update(@PathVariable String id, @RequestBody UsuarioRequestDTO usuarioRequest){
        return service.update(id, usuarioRequest);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Excluir um Usuário",
            description = "Este endpoint permite excluir um usuário com base no ID fornecido."
    )
    public void delete(@PathVariable String id){
        service.delete(id);
    }
}
