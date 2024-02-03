package io.github.Marcky404.Biblioteca.domain;

import io.github.Marcky404.Biblioteca.domain.enuns.Tipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Telefone {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ddd;
    private String numero;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

}
