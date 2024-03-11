package io.github.Marcky404.Biblioteca.controller;

import io.github.Marcky404.Biblioteca.domain.request.EnderecoRequest;
import io.github.Marcky404.Biblioteca.exception.BusinessException;
import io.github.Marcky404.Biblioteca.service.EnderecoService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/endereco")
@Tag(name = "Endereco", description = "Endpoints responsáveis por gerenciar enderecos")
public class EnderecoController {

    private final EnderecoService service;

    @Operation(summary = "Atualizar Endereço", description = "Endpoint para atualizar um endereço existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "409", description = "Conflito com dados já cadastrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "500", description = "Sistema Indisponível",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoRequest> atualizar(@PathVariable("id") Long id, @RequestBody @Valid EnderecoRequest enderecoRequest) {
        return ResponseEntity.ok(service.atualizar(id, enderecoRequest));
    }

    @Operation(summary = "Deleta Endereço", description = "Endpoint para deletar um endereço existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Endereço deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "500", description = "Sistema Indisponível",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class)))
    })
    @DeleteMapping("/{enderecoId}/cliente/{clienteId}")
    public ResponseEntity deletar(@PathVariable("enderecoId") Long enderecoId, @PathVariable("clienteId") Long clienteId) {
        service.deletar(enderecoId, clienteId);
        return ResponseEntity.noContent().build();
    }

}