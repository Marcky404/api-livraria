package io.github.Marcky404.Biblioteca.controller;

import io.github.Marcky404.Biblioteca.domain.Cliente;
import io.github.Marcky404.Biblioteca.domain.request.ClienteAtualizarRequest;
import io.github.Marcky404.Biblioteca.domain.request.ClienteRequest;
import io.github.Marcky404.Biblioteca.domain.response.ClienteDesativarResponse;
import io.github.Marcky404.Biblioteca.domain.response.ClienteResponse;
import io.github.Marcky404.Biblioteca.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService service;


    @PostMapping
    public ResponseEntity salvar(@RequestBody @Valid ClienteRequest clienteRequest) {
        Cliente cliente = service.salvar(clienteRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizar(@PathVariable("id") Long id, @RequestBody @Valid ClienteAtualizarRequest clienteAtualizarRequest) {
    return ResponseEntity.ok(service.atualizar(id, clienteAtualizarRequest));

    }


    @PutMapping("/desativar")
    public ResponseEntity<ClienteDesativarResponse> desativarCliente(@RequestBody @Valid ClienteDesativarResponse desativarResponse){
        return ResponseEntity.ok(service.desativarCliente(desativarResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable("id") Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
