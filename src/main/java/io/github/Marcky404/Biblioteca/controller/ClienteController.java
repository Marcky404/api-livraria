package io.github.Marcky404.Biblioteca.controller;

import io.github.Marcky404.Biblioteca.domain.Cliente;
import io.github.Marcky404.Biblioteca.domain.enums.Status;
import io.github.Marcky404.Biblioteca.domain.request.ClienteAtualizarRequest;
import io.github.Marcky404.Biblioteca.domain.request.ClienteRequest;
import io.github.Marcky404.Biblioteca.domain.response.ClienteResponse;
import io.github.Marcky404.Biblioteca.exception.BusinessException;
import io.github.Marcky404.Biblioteca.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
@Tag(name = "Cliente", description = "Endpoints responsáveis por gerenciar clientes")
public class ClienteController {

    private final ClienteService service;

    @Operation(summary = "Criar Cliente", description = "Endpoint para criar um novo cliente.")
    @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso")
    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid ClienteRequest clienteRequest) {
        Cliente cliente = service.salvar(clienteRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Buscar Cliente", description = "Endpoint para buscar um cliente existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente Encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "500", description = "Sistema Indisponível",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @Operation(summary = "Atualizar Cliente", description = "Endpoint para atualizar um cliente existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "409", description = "Conflito com dados já cadastrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "500", description = "Sistema Indisponível",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizar(@PathVariable("id") Long id, @RequestBody @Valid ClienteAtualizarRequest clienteAtualizarRequest) {
        return ResponseEntity.ok(service.atualizar(id, clienteAtualizarRequest));

    }

    @Operation(summary = "Desativar Cliente", description = "Endpoint para desativar um cliente existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente desativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "409", description = "Conflito com dados já cadastrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "500", description = "Sistema Indisponível",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class)))
    })

    @PutMapping("/{id}/desativar")
    public ResponseEntity<ClienteResponse> desativarCliente(@PathVariable("id") Long id, @RequestParam("status") Status status) {
        return ResponseEntity.ok(service.desativarCliente(id, status));
    }

    @Operation(summary = "Deletar Cliente", description = "Endpoint para deletadar um cliente existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Sistema Indisponível")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
