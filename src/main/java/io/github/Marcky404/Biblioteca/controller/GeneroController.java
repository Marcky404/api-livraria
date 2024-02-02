package io.github.Marcky404.Biblioteca.controller;

import io.github.Marcky404.Biblioteca.domain.Genero;
import io.github.Marcky404.Biblioteca.domain.request.GeneroRequest;
import io.github.Marcky404.Biblioteca.service.GeneroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genero")
public class GeneroController {

    private final GeneroService service;

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

    @GetMapping
    public ResponseEntity<Genero> buscar(@RequestParam("id") Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<List<Genero>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genero> atualizar(@PathVariable("id") Long id, @RequestBody GeneroRequest generoRequest) {
        return ResponseEntity.ok(service.atualizar(id, generoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable("id") Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
