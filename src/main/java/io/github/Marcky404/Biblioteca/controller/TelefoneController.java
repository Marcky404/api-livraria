package io.github.Marcky404.Biblioteca.controller;

import io.github.Marcky404.Biblioteca.domain.request.TelefoneRequest;
import io.github.Marcky404.Biblioteca.exception.BusinessException;
import io.github.Marcky404.Biblioteca.service.TelefoneService;
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
@RequestMapping("/telefone")
@Tag(name = "Telefone", description = "Endpoints responsáveis por gerenciar telefones")
public class TelefoneController {

    private final TelefoneService service;

    @Operation(summary = "Atualizar Telefone", description = "Endpoint para atualizar um telefone existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "409", description = "Conflito com dados já cadastrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "500", description = "Sistema Indisponível",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<TelefoneRequest> atualizar(@PathVariable("id") Long id, @RequestBody @Valid TelefoneRequest telefoneRequest) {
        return ResponseEntity.ok(service.atualizar(id, telefoneRequest));

    }

    @Operation(summary = "Deletar Telefone", description = "Endpoint para deletadar um telefone existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Telefone deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Sistema Indisponível")
    })
    @DeleteMapping( "/{telefoneId}/cliente/{clienteId}")
    public ResponseEntity deletar(@PathVariable("telefoneId") Long telefoneId,@PathVariable("clienteId") Long clienteId ){
        service.deletar(telefoneId, clienteId);
        return ResponseEntity.noContent().build();
    }
}
