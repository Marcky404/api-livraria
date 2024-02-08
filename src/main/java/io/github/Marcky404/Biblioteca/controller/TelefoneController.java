package io.github.Marcky404.Biblioteca.controller;

import io.github.Marcky404.Biblioteca.domain.request.TelefoneRequest;
import io.github.Marcky404.Biblioteca.service.TelefoneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/telefone")
public class TelefoneController {

    private final TelefoneService service;

    @PutMapping("/{id}")
    public ResponseEntity<TelefoneRequest> atualizar(@PathVariable("id") Long id, @RequestBody @Valid TelefoneRequest telefoneRequest) {
        return ResponseEntity.ok(service.atualizar(id, telefoneRequest));

    }
}
