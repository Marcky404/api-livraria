package io.github.Marcky404.Biblioteca.domain.request;

import io.github.Marcky404.Biblioteca.domain.Livro;
import lombok.Data;

import java.util.List;


@Data
public class LivroLoteRequest {
    private List<Livro> livros;
}
