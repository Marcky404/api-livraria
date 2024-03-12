package io.github.Marcky404.Biblioteca.domain;

import io.github.Marcky404.Biblioteca.domain.enums.Disponibilidade;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Livro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema(title = "Titulo do livro", example = "Quebre a cabeça Java", requiredMode = Schema.RequiredMode.REQUIRED)
    private String titulo;
    @Schema(title = "Autor do livro", example = "Luan Alves", requiredMode = Schema.RequiredMode.REQUIRED)
    private String autor;
    @Column(name = "ano_publicacao")
    @Schema(title = "Ano de Publicação do livro", example = "2011", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer anoPublicacao;
    @Schema(title = "ISBN do livro", example = "978-77-325-1234-8", requiredMode = Schema.RequiredMode.REQUIRED)
    private String isbn;
    @ManyToOne
    @Schema(title = "Genero do livro", requiredMode = Schema.RequiredMode.REQUIRED)
    @JoinColumn(name = "genero_id")
    private Genero genero;
    @Column(name = "numero_paginas")
    @Schema(title = "Número e paginas do livro", example = "345", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer numeroPaginas;
    @Enumerated(EnumType.STRING)
    @Schema(title = "Disponibilidade do livro", example = "DISPONIVEL", requiredMode = Schema.RequiredMode.REQUIRED)
    private Disponibilidade disponibilidade;
    @Schema(title = "Valor do livro", example = "34.35", requiredMode = Schema.RequiredMode.REQUIRED)
    private Double valor;
    @Column(name = "CREATED", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime created;
}
