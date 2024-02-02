package io.github.Marcky404.Biblioteca.controller;

import io.github.Marcky404.Biblioteca.domain.Genero;
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


}
