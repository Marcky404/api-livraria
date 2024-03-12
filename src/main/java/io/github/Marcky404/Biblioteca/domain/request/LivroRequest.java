package io.github.Marcky404.Biblioteca.domain.request;

import io.github.Marcky404.Biblioteca.domain.Genero;
import io.github.Marcky404.Biblioteca.domain.enums.Disponibilidade;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroRequest {

    @Schema(title = "Titulo do livro", example = "Quebre a cabeça Java", requiredMode = Schema.RequiredMode.REQUIRED)
    private String titulo;
    @Schema(title = "Ano de Publicação do livro", example = "2011", requiredMode = Schema.RequiredMode.REQUIRED)
    private String autor;
    @Schema(title = "ISBN do livro", example = "978-77-325-1234-8", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer anoPublicacao;
    @Schema(title = "ISBN do livro", example = "978-77-325-1234-8", requiredMode = Schema.RequiredMode.REQUIRED)
    private String isbn;
    @Schema(title = "Genero do livro", requiredMode = Schema.RequiredMode.REQUIRED)
    private Genero genero;
    @Schema(title = "Número e paginas do livro", example = "345", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer numeroPaginas;
    @Schema(title = "Disponibilidade do livro", example = "DISPONIVEL", requiredMode = Schema.RequiredMode.REQUIRED)
    private Disponibilidade disponibilidade;
    @Schema(title = "Valor do livro", example = "34.35", requiredMode = Schema.RequiredMode.REQUIRED)
    private Double valor;

}
