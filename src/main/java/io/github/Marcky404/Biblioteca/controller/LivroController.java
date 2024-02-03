package io.github.Marcky404.Biblioteca.controller;

import io.github.Marcky404.Biblioteca.domain.Livro;
import io.github.Marcky404.Biblioteca.domain.request.LivroLoteRequest;
import io.github.Marcky404.Biblioteca.domain.request.LivroQuantidadeTotalGenero;
import io.github.Marcky404.Biblioteca.domain.request.LivroRequest;
import io.github.Marcky404.Biblioteca.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/livro")
public class LivroController {


    private final LivroService service;


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

    @PostMapping("/salvar-lote")
    public ResponseEntity<Void> salvarEmLote(@RequestBody LivroLoteRequest livroLoteRequest) {
        service.salvarEmLote(livroLoteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscaPorID(@PathVariable("id") Long id) {
        Livro livro = service.buscar(id);
        return ResponseEntity.ok(livro);
    }

    @GetMapping("/lista-maior-valor")
    public ResponseEntity<List<Livro>> buscarListaDeLivrosOrdenadoPorMaiorValor() {
        return ResponseEntity.ok(service.buscarListaDeLivrosOrdenadoPorMaiorValor());
    }

    @GetMapping("/lista-menor-valor")
    public ResponseEntity<List<Livro>> buscarListaDeLivrosOrdenadoPorMenorValor() {
        return ResponseEntity.ok(service.buscarListaDeLivrosOrdenadoPorMenorValor());
    }

    @GetMapping("/lista-por-genero/{generoID}")
    public ResponseEntity<List<Livro>> buscarListaDeLivrosPorGenero(@PathVariable("generoID") Long generoID) {
        return ResponseEntity.ok(service.buscarListaDeLivrosPorGenero(generoID));
    }

    @GetMapping("/quantidade-total-genero")
    public ResponseEntity<List<LivroQuantidadeTotalGenero>> buscarLivroQuantidadeTotalGenero(){
        return ResponseEntity.ok(service.buscarLivroQuantidadeTotalGenero());
    }



    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable("id") Long id, @RequestBody LivroRequest livroRequest) {
        Livro livro = service.atualiar(id, livroRequest);
        return ResponseEntity.ok(livro);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }




}
