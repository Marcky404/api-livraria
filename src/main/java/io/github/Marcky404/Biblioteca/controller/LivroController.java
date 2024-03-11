package io.github.Marcky404.Biblioteca.controller;

import io.github.Marcky404.Biblioteca.domain.Livro;
import io.github.Marcky404.Biblioteca.domain.request.LivroLoteRequest;
import io.github.Marcky404.Biblioteca.domain.request.LivroQuantidadeTotalGenero;
import io.github.Marcky404.Biblioteca.domain.request.LivroRequest;
import io.github.Marcky404.Biblioteca.exception.BusinessException;
import io.github.Marcky404.Biblioteca.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/livro")
@Tag(name = "Livro", description = "Endpoints responsáveis por gerenciar Livros")
public class LivroController {


    private final LivroService service;


    @Operation(summary = "Criar Livro", description = "Endpoint para criar um novo livro.")
    @ApiResponse(responseCode = "201", description = "Livro criado com sucesso")
    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Livro livro) {
        service.salvar(livro);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(livro.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Criar Livro em Lote", description = "Endpoint para criar varios novos livros.")
    @ApiResponse(responseCode = "201", description = "Livros criados com sucesso")
    @PostMapping("/salvar-lote")
    public ResponseEntity<Void> salvarEmLote(@RequestBody LivroLoteRequest livroLoteRequest) {
        service.salvarEmLote(livroLoteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Buscar Livro", description = "Endpoint para buscar um livro existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro Encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "500", description = "Sistema Indisponível",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscaPorID(@PathVariable("id") Long id) {
        Livro livro = service.buscar(id);
        return ResponseEntity.ok(livro);
    }

    @Operation(summary = "Buscar Livro por ordem de maior valor", description = "Endpoint para buscar livros existentes por ordem de maior valor.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livros Encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "500", description = "Sistema Indisponível",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class)))
    })
    @GetMapping("/lista-maior-valor")
    public ResponseEntity<List<Livro>> buscarListaDeLivrosOrdenadoPorMaiorValor() {
        return ResponseEntity.ok(service.buscarListaDeLivrosOrdenadoPorMaiorValor());
    }

    @Operation(summary = "Buscar Livro por ordem de menor valor", description = "Endpoint para buscar livros existentes por ordem de menor valor.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livros Encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "500", description = "Sistema Indisponível",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class)))
    })
    @GetMapping("/lista-menor-valor")
    public ResponseEntity<List<Livro>> buscarListaDeLivrosOrdenadoPorMenorValor() {
        return ResponseEntity.ok(service.buscarListaDeLivrosOrdenadoPorMenorValor());
    }

    @Operation(summary = "Buscar Livros por Genero", description = "Endpoint para buscar livros existentes por Genero.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livros Encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "500", description = "Sistema Indisponível",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class)))
    })
    @GetMapping("/lista-por-genero/{generoID}")
    public ResponseEntity<List<Livro>> buscarListaDeLivrosPorGenero(@PathVariable("generoID") Long generoID) {
        return ResponseEntity.ok(service.buscarListaDeLivrosPorGenero(generoID));
    }

    @Operation(summary = "Buscar a Quantidade total de Livros por Genero", description = "Endpoint para buscar a quantidade de livros existentes por Genero.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livros Encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "500", description = "Sistema Indisponível",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class)))
    })
    @GetMapping("/quantidade-total-genero")
    public ResponseEntity<List<LivroQuantidadeTotalGenero>> buscarLivroQuantidadeTotalGenero() {
        return ResponseEntity.ok(service.buscarLivroQuantidadeTotalGenero());
    }

    @Operation(summary = "Atualizar Livro", description = "Endpoint para atualizar um livro existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "409", description = "Conflito com dados já cadastrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
            @ApiResponse(responseCode = "500", description = "Sistema Indisponível",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable("id") Long id, @RequestBody LivroRequest livroRequest) {
        Livro livro = service.atualizar(id, livroRequest);
        return ResponseEntity.ok(livro);
    }

    @Operation(summary = "Deletar Livro", description = "Endpoint para deletadar um livro existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Sistema Indisponível")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}