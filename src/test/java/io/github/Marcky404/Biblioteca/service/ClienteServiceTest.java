package io.github.Marcky404.Biblioteca.service;


import io.github.Marcky404.Biblioteca.domain.Cliente;
import io.github.Marcky404.Biblioteca.domain.ClienteMock;
import io.github.Marcky404.Biblioteca.domain.EnderecoMock;
import io.github.Marcky404.Biblioteca.domain.enums.Status;
import io.github.Marcky404.Biblioteca.exception.BusinessException;
import io.github.Marcky404.Biblioteca.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    ClienteService clienteService;

    @Mock
    ClienteRepository clienteRepository;

    @Mock
    EnderecoService enderecoService;

    Status status;

    @BeforeEach
    void setUp() {
        status = Status.INATIVO;
    }

    @Test
    void salvarSucesso() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(ClienteMock.getCliente());
        when(enderecoService.buscarEndereco(any())).thenReturn(EnderecoMock.getEndereco());

        var resposta = clienteService.salvar(ClienteMock.getClienteRequest());

        assertThat(resposta).isNotNull();
        assertThat(resposta.getId()).isEqualTo(1L);
        assertThat(resposta.getNome()).isEqualTo("João");
        assertThat(resposta.getSobrenome()).isEqualTo("Da Silva");
        assertThat(resposta.getCpf()).isEqualTo("123.456.789-00");
        assertThat(resposta.getStatus()).isEqualTo(ClienteMock.getCliente().getStatus());
        assertThat(resposta.getEmail()).isEqualTo("joao@email.com");
        assertThat(resposta.getSexo()).isEqualTo(ClienteMock.getCliente().getSexo());
        assertThat(resposta.getDataNascimento()).isEqualTo(LocalDate.of(2000, 10, 8));
    }

    @Test
    void buscarSucesso() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.ofNullable(ClienteMock.getCliente()));
        var resposta = clienteService.buscar(anyLong());

        assertThat(resposta).isNotNull();
        assertThat(resposta.getId()).isEqualTo(1L);
        assertThat(resposta.getNome()).isEqualTo("João");
        assertThat(resposta.getSobrenome()).isEqualTo("Da Silva");
        assertThat(resposta.getCpf()).isEqualTo("123.456.789-00");
        assertThat(resposta.getStatus()).isEqualTo(ClienteMock.getCliente().getStatus());
        assertThat(resposta.getEmail()).isEqualTo("joao@email.com");
        assertThat(resposta.getSexo()).isEqualTo(ClienteMock.getCliente().getSexo());
        assertThat(resposta.getDataNascimento()).isEqualTo(LocalDate.of(2000, 10, 8));
    }

    @Test
    void buscar_quandoClienteNaoLocalizado_retornaBusinessException() {
        var exception = assertThrows(BusinessException.class, () -> clienteService.buscar(ClienteMock.LONG_ID));
        assertThat(exception.getMessage()).isEqualTo("Cliente não encontrado");
    }

    @Test
    void atulizarSucesso() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.ofNullable(ClienteMock.getCliente()));
        var resposta = clienteService.atualizar(anyLong(), ClienteMock.getClienteAtualizarRequest());

        assertThat(resposta).isNotNull();
        assertThat(resposta.getNome()).isEqualTo("Maria");
        assertThat(resposta.getSobrenome()).isEqualTo("De Souza");
        assertThat(resposta.getEmail()).isEqualTo("maria@exemplo.com");
        assertThat(resposta.getCpf()).isEqualTo("123.456.789-00");
        assertThat(resposta.getStatus()).isEqualTo(ClienteMock.getCliente().getStatus());
        assertThat(resposta.getSexo()).isEqualTo(ClienteMock.getCliente().getSexo());
        assertThat(resposta.getDataNascimento()).isEqualTo(LocalDate.of(2000, 10, 8));

    }

    @Test
    void deleteSucesso() {
        clienteService.deletar(anyLong());
        verify(clienteRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void desativarCliente() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.ofNullable(ClienteMock.getCliente()));
        var resposta = clienteService.desativarCliente(anyLong(), status);

        assertThat(resposta).isNotNull();

        assertThat(resposta.getStatus()).isEqualTo(Status.INATIVO);
    }
}
