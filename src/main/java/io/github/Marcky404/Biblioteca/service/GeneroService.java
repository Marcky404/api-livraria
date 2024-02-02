package io.github.Marcky404.Biblioteca.service;

import io.github.Marcky404.Biblioteca.domain.Genero;
import io.github.Marcky404.Biblioteca.domain.request.GeneroRequest;
import io.github.Marcky404.Biblioteca.repository.GeneroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GeneroService {

    private final GeneroRepository repository;

    public void salvar(Genero genero) {
        repository.save(genero);
    }

    public Genero buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Genero não localizado!"));
    }

    @Transactional
    public Genero atualizar(Long id, GeneroRequest generoRequest) {

        Genero genero = buscar(id);
        genero.setGenero(generoRequest.getGenero());

        return genero;
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public List<Genero> listarTodos() {
        return repository.findAll();
    }
}
