package io.github.Marcky404.Biblioteca.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "livro_genero")
@Entity
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema(title = "Genero do livro",example = "DRAMA", requiredMode = Schema.RequiredMode.REQUIRED)
    private String genero;

    public void setGenero(String genero) {
        this.genero = genero.toUpperCase();
    }
}
