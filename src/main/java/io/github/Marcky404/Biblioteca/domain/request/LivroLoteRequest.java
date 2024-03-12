package io.github.Marcky404.Biblioteca.domain.request;

import io.github.Marcky404.Biblioteca.domain.Livro;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;


@Data
public class LivroLoteRequest {
    @Schema(title = "Livros em lote", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<Livro> livros;
}
