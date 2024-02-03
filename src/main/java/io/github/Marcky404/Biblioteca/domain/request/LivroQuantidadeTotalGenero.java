package io.github.Marcky404.Biblioteca.domain.request;

import io.github.Marcky404.Biblioteca.domain.Genero;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@NoArgsConstructor
public class LivroQuantidadeTotalGenero {

    private Long quantidade;
    private Double total;
    private Genero genero;

    public LivroQuantidadeTotalGenero(Long quantidade, Double total, Genero genero) {
        this.quantidade = quantidade;
        this.total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.genero = genero;
    }
}
