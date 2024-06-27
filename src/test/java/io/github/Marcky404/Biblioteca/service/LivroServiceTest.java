package io.github.Marcky404.Biblioteca.service;

import io.github.Marcky404.Biblioteca.domain.Genero;
import io.github.Marcky404.Biblioteca.domain.Livro;
import io.github.Marcky404.Biblioteca.domain.LivroMock;
import io.github.Marcky404.Biblioteca.domain.request.LivroLoteRequest;
import io.github.Marcky404.Biblioteca.domain.request.LivroQuantidadeTotalGenero;
import io.github.Marcky404.Biblioteca.exception.BusinessException;
import io.github.Marcky404.Biblioteca.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {

    public static final Long LONG_ID = 1L;
    @InjectMocks
    LivroService livroService;

    @Mock
    LivroRepository livroRepository;

    @Mock
    GeneroService generoService;

    Genero genero;

    @Mock
    LivroLoteRequest livroLoteRequest;

    List<Livro> livroList;

    List<LivroQuantidadeTotalGenero> livroQuantidadeTotalGenero;

    @BeforeEach
    void setUp() {
        genero = new Genero(LONG_ID, "TECNOLOGIA");
    }

    @Test
    void salvar_sucesso() {
        when(livroRepository.save(any())).thenReturn(LivroMock.getLivro());
        livroService.salvar(LivroMock.getLivro());

        verify(livroRepository, times(1)).save(any());
    }

    @Test
    void buscarSucesso() {
        when(livroRepository.findById(LivroMock.LONG_ID)).thenReturn(Optional.ofNullable(LivroMock.getLivro()));
        var resposta = livroService.buscar(LivroMock.LONG_ID);

        assertThat(resposta).isEqualTo(LivroMock.getLivro());

    }

    @Test
    void buscarBusinessException() {
        var exception = assertThrows(BusinessException.class, () -> livroService.buscar(1L));

        assertThat(exception.getMessage()).isEqualTo("Livro não encontrado");
    }

    @Test
    void atualizarSucesso() {
        when(livroRepository.findById(LivroMock.LONG_ID)).thenReturn(Optional.ofNullable(LivroMock.getLivro()));
        when(generoService.buscar(LivroMock.getLivroRequest().getGenero().getId())).thenReturn(genero);
        var resposta = livroService.atualizar(LivroMock.LONG_ID, LivroMock.getLivroRequest());

        assertThat(resposta).isNotNull();
        assertThat(resposta.getAutor()).isEqualTo("Luan Alves");
        assertThat(resposta.getIsbn()).isEqualTo("978-77-355-1234-9");
        assertThat(resposta.getAnoPublicacao()).isEqualTo(2014);
        assertThat(resposta.getValor()).isEqualTo(40.35);
        assertThat(resposta.getTitulo()).isEqualTo("JavaScript");
        assertThat(resposta.getNumeroPaginas()).isEqualTo(500);
        assertThat(resposta.getDisponibilidade()).isEqualTo(LivroMock.getLivroRequest().getDisponibilidade());
        assertThat(resposta.getGenero()).isEqualTo(LivroMock.getLivroRequest().getGenero());

    }

    @Test
    void deletarSucesso() {
        livroService.deletar(LivroMock.LONG_ID);
        verify(livroRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void salvarEmLoteSucesso() {
        livroService.salvarEmLote(livroLoteRequest);
        verify(livroRepository, times(1)).saveAll(livroLoteRequest.getLivros());
    }

    @Test
    void buscarListaDeLivrosOrdenadoPorMaiorValorSucesso() {
        when(livroRepository.findByOrderByValorDesc()).thenReturn(livroList);
        var resposta = livroService.buscarListaDeLivrosOrdenadoPorMaiorValor();

        assertThat(resposta).isEqualTo(livroList);
    }

    @Test
    void buscarListaDeLivrosOrdenadoPorMenorValorSucesso() {
        when(livroRepository.findByLivroOrderByValorAsc()).thenReturn(livroList);
        var resposta = livroService.buscarListaDeLivrosOrdenadoPorMenorValor();

        assertThat(resposta).isEqualTo(livroList);
    }

    @Test
    void buscarListaDeLivrosPorGeneroSucesso() {
        when(livroRepository.findByLivroByGenero(anyLong())).thenReturn(livroList);
        var resposta = livroService.buscarListaDeLivrosPorGenero(anyLong());

        assertThat(resposta).isEqualTo(livroList);
    }

    @Test
    void buscarLivroQuantidadeTotalGenero() {
        when(livroRepository.findByLivroQuantidadeTotalGenero()).thenReturn(livroQuantidadeTotalGenero);
        var resposta = livroService.buscarLivroQuantidadeTotalGenero();

        assertThat(resposta).isEqualTo(livroQuantidadeTotalGenero);
    }


}
