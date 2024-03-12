package io.github.Marcky404.Biblioteca.controller;

import io.github.Marcky404.Biblioteca.domain.Genero;
import io.github.Marcky404.Biblioteca.domain.request.GeneroRequest;
import io.github.Marcky404.Biblioteca.exception.BusinessException;
import io.github.Marcky404.Biblioteca.service.GeneroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genero")
@Tag(name = "Genero", description = "Endpoints responsáveeis por gerenciar generos")
public class GeneroController {

    private final GeneroService service;

    @Operation(summary = "Criar Genero", description = "Endpoint para criar um novo genero.")
    @ApiResponse(responseCode = "201", description = "Genero criado com sucesso")
    @PostMapping
    public ResponseEntity salvar(@RequestBody Genero genero) {
        service.salvar(genero);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(genero.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Buscar Genero", description = "Endpoint para buscar um genero existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genero atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "409", description = "Conflito com dados já cadastrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "500", description = "Sistema Indisponível",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class)))
    })
    @GetMapping
    public ResponseEntity<Genero> buscar(@RequestParam("id") Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @Operation(summary = "Buscar Todos os Generos", description = "Endpoint para buscar todos os generos existentes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genero atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "409", description = "Conflito com dados já cadastrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "500", description = "Sistema Indisponível",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class)))
    })
    @GetMapping("/listar-todos")
    public ResponseEntity<List<Genero>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @Operation(summary = "Atualizar Genero", description = "Endpoint para atualizar um genero existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genero atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "409", description = "Conflito com dados já cadastrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "500", description = "Sistema Indisponível",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Genero> atualizar(@PathVariable("id") Long id, @RequestBody GeneroRequest generoRequest) {
        return ResponseEntity.ok(service.atualizar(id, generoRequest));
    }

    @Operation(summary = "Deletar Genero", description = "Endpoint para deletadar um genero existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Genero deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Sistema Indisponível")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable("id") Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}