package io.github.Marcky404.Biblioteca.controller;

import io.github.Marcky404.Biblioteca.domain.Genero;
import io.github.Marcky404.Biblioteca.domain.request.GeneroRequest;
import io.github.Marcky404.Biblioteca.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private GeneroService service;


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
    public ResponseEntity<Genero> buscar(@RequestParam("id") Long id){
        return ResponseEntity.ok(service.buscar(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Genero> atualizar(@PathVariable("id") Long id, @RequestBody GeneroRequest generoRequest){
        return ResponseEntity.ok(service.atualizar(id, generoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable("id") Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
