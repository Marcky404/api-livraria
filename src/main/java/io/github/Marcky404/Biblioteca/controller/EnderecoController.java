package io.github.Marcky404.Biblioteca.controller;

import io.github.Marcky404.Biblioteca.domain.request.EnderecoRequest;
import io.github.Marcky404.Biblioteca.service.EnderecoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoService service;



    @PutMapping("/{id}")
    public ResponseEntity<EnderecoRequest> atualizar(@PathVariable("id") Long id, @RequestBody @Valid EnderecoRequest enderecoRequest) {
        return ResponseEntity.ok(service.atualizar(id, enderecoRequest));
    }

}
