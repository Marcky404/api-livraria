package io.github.Marcky404.Biblioteca.domain;

import io.github.Marcky404.Biblioteca.domain.enuns.Disponibilidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Livro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    @Column(name = "ano_publicacao")
    private Integer anoPublicacao;
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;

    @Column(name = "numero_paginas")
    private Integer numeroPaginas;
    @Enumerated(EnumType.STRING)
    private Disponibilidade disponibilidade;
    private Double valor;
    @Column(name = "CREATED", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime created;
}
