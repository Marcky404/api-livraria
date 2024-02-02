package io.github.Marcky404.Biblioteca.repository;

import io.github.Marcky404.Biblioteca.domain.Livro;
import io.github.Marcky404.Biblioteca.domain.enuns.Genero;
import io.github.Marcky404.Biblioteca.domain.request.LivroQuantidadeTotalGenero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByOrderByValorDesc();

    @Query("SELECT l FROM Livro l ORDER BY l.valor ASC")
    List<Livro> findByLivroOrderByValorAsc();

    @Query("SELECT l FROM Livro l WHERE l.genero = :genero")
    List<Livro> findByLivroByGenero(@Param("genero") Genero genero);

    @Query("SELECT new io.github.Marcky404.Biblioteca.domain.request." +
            "LivroQuantidadeTotalGenero(count(*) as quantidade, sum(l.valor)  " +
            "as total, l.genero) from Livro l GROUP BY l.genero")
    List<LivroQuantidadeTotalGenero> findByLivroQuantidadeTotalGenero();
    
}
