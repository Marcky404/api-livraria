package io.github.Marcky404.Biblioteca.domain.request;

import io.github.Marcky404.Biblioteca.domain.Genero;
import io.github.Marcky404.Biblioteca.domain.enums.Disponibilidade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroRequest {

    private String titulo;
    private String autor;
    private Integer anoPublicacao;
    private String isbn;
    private Genero genero;
    private Integer numeroPaginas;
    private Disponibilidade disponibilidade;
    private Double valor;

}
