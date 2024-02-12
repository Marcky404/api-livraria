package io.github.Marcky404.Biblioteca.domain;

import io.github.Marcky404.Biblioteca.domain.enums.TipoTelefone;
import io.github.Marcky404.Biblioteca.domain.request.TelefoneRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Telefone {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ddd;
    private String numero;
    @Enumerated(EnumType.STRING)
    private TipoTelefone tipoTelefone;


    public Telefone(Integer ddd, String numero, TipoTelefone tipoTelefone) {
        this.ddd = ddd;
        this.numero = numero;
        this.tipoTelefone = tipoTelefone;
    }


}
