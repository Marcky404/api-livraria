package io.github.Marcky404.Biblioteca.service;

import io.github.Marcky404.Biblioteca.domain.Genero;
import io.github.Marcky404.Biblioteca.domain.request.GeneroRequest;
import io.github.Marcky404.Biblioteca.exception.BusinessException;
import io.github.Marcky404.Biblioteca.repository.GeneroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GeneroServiceTest {

    public static final Long LONG_ID = 1L;

    @InjectMocks
    GeneroService generoService;
    @Mock
    GeneroRepository repository;
    Genero genero;

    @BeforeEach
    void setUp() {
        genero = new Genero(LONG_ID, "ROMANCE");
    }

    @Test
    void salvarSucesso() {
        when(repository.save(any())).thenReturn(genero);
        generoService.salvar(genero);
        verify(repository, times(1)).save(genero);
    }

    @Test
    void buscarSucesso() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(genero));
        var resposta = generoService.buscar(LONG_ID);
        assertThat(resposta).isEqualTo(genero);
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void buscarBusinessException() {
        var exception = assertThrows(BusinessException.class, () -> generoService.buscar(1L));

        assertThat(exception.getMessage()).isEqualTo("Gênero não encontrado");
    }

    @Test
    void atualizarSucesso() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(genero));
        var generoRequest = new GeneroRequest("tecnologia");
        var generoAtualizado = generoService.atualizar(LONG_ID, generoRequest);
        assertThat(generoAtualizado.getGenero()).isEqualTo(generoRequest.getGenero().toUpperCase());
    }

    @Test
    void deletarSucesso() {
        generoService.deletar(LONG_ID);
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void listarTodosSucesso() {

        var generos = asList(new Genero(1L,"ROMANCE"),
                             new Genero(2L, "TECNOLOGIA"),
                             new Genero(3L, "FICÇÃO"));

        when(repository.findAll()).thenReturn(generos);

        var generoList = generoService.listarTodos();
        assertThat(generoList).hasSize(3);
        verify(repository, times(1)).findAll();
    }

    @Test
    void listarTodosSucesso2() {

        var generos = asList(new Genero(1L,"ROMANCE"),
                new Genero(2L, "TECNOLOGIA"),
                new Genero(3L, "FICÇÃO"));

        when(repository.findAll()).thenReturn(generos);

        var generoList = generoService.listarTodos();

        IntStream.range(0, generoList.size()).forEach(i -> {
            assertThat(generoList).isNotNull();
            assertThat(generoList.get(i).getGenero()).isEqualTo(generos.get(i).getGenero());
        });
    }

}
