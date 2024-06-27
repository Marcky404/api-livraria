package io.github.Marcky404.Biblioteca.service;

import io.github.Marcky404.Biblioteca.domain.Cliente;
import io.github.Marcky404.Biblioteca.domain.Endereco;
import io.github.Marcky404.Biblioteca.domain.ClienteMock;
import io.github.Marcky404.Biblioteca.domain.EnderecoMock;
import io.github.Marcky404.Biblioteca.domain.request.EnderecoRequest;
import io.github.Marcky404.Biblioteca.domain.response.EnderecoResponse;
import io.github.Marcky404.Biblioteca.exception.BusinessException;
import io.github.Marcky404.Biblioteca.repository.ClienteRepository;
import io.github.Marcky404.Biblioteca.repository.EnderecoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTest {

    @InjectMocks
    EnderecoService enderecoService;

    @Mock
    EnderecoRepository enderecoRepository;

    @Mock
    ClienteRepository clienteRepository;
    @Mock
    RestTemplate restTemplate;


    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(enderecoService, "endpoint", EnderecoMock.VIA_CEP_URL);
    }


    @Test
    void buscar_sucesso() {
        when(enderecoRepository.findById(anyLong())).thenReturn(Optional.of(EnderecoMock.getEndereco()));
        Endereco resposta = enderecoService.buscar(EnderecoMock.LONG_ID);
        assertThat(resposta).isNotNull();
        assertThat(resposta.getCep()).isEqualTo("40285-001");
        assertThat(resposta.getNumero()).isEqualTo(123);
        assertThat(resposta.getLogradouro()).isEqualTo("Dr.João Freitas");
        assertThat(resposta.getComplemento()).isEqualTo("Apartamento 101");
        assertThat(resposta.getBairro()).isEqualTo("Brotas");
        assertThat(resposta.getLocalidade()).isEqualTo("Salvador");
        assertThat(resposta.getUf()).isEqualTo("BA");
        assertThat(resposta.getDestinatario()).isEqualTo("João Silva");

    }

    @Test
    void buscar_quandoEnderecoNaoLocalizado_retornaBusinessException() {
        var exception = assertThrows(BusinessException.class, () -> enderecoService.buscar(EnderecoMock.LONG_ID));
        assertThat(exception.getMessage()).isEqualTo("Endereco não encontrado");
    }

    @Test
    void buscar_enderecoViaCep_sucesso() {
        when(restTemplate.getForObject(anyString(), any())).thenReturn(EnderecoMock.getEnderecoViaCep());
        Endereco resposta = enderecoService.buscarEndereco(EnderecoMock.CEP);
        assertThat(resposta).isNotNull();
        assertThat(resposta.getCep()).isEqualTo("01001-000");
        assertThat(resposta.getLogradouro()).isEqualTo("Praça da Sé");
        assertThat(resposta.getComplemento()).isEqualTo("lado ímpar");
        assertThat(resposta.getBairro()).isEqualTo("Sé");
        assertThat(resposta.getLocalidade()).isEqualTo("São Paulo");
        assertThat(resposta.getUf()).isEqualTo("SP");
    }

    @Test
    void atualizar_sucesso() {
        when(enderecoRepository.findById(anyLong())).thenReturn(Optional.of(EnderecoMock.getEndereco()));
        EnderecoResponse resposta = enderecoService.atualizar(EnderecoMock.LONG_ID, EnderecoMock.getEnderecoRequest());
        assertThat(resposta).isNotNull();
        assertThat(resposta.getCep()).isEqualTo("40285-001");
        assertThat(resposta.getNumero()).isEqualTo(31);
        assertThat(resposta.getLogradouro()).isEqualTo("Dr.João Borjes");
        assertThat(resposta.getComplemento()).isEqualTo("Casa");
        assertThat(resposta.getBairro()).isEqualTo("Bosque");
        assertThat(resposta.getLocalidade()).isEqualTo("Campinas");
        assertThat(resposta.getUf()).isEqualTo("SP");
        assertThat(resposta.getDestinatario()).isEqualTo("Pedro Henrique");
    }


    @Test
    void atualizar_quandoCepEnderecoDiferenteCepEnderecoRequest_sucesso() {
        when(enderecoRepository.findById(anyLong())).thenReturn(Optional.of(EnderecoMock.getEndereco()));
        when(restTemplate.getForObject(anyString(), any())).thenReturn(EnderecoMock.getEndereco());
        EnderecoRequest enderecoRequest = EnderecoMock.getEnderecoRequest();
        enderecoRequest.setCep("01153000");


        EnderecoResponse resposta = enderecoService.atualizar(EnderecoMock.LONG_ID, enderecoRequest);
        assertThat(resposta).isNotNull();
        assertThat(resposta.getCep()).isEqualTo("40285-001");
        assertThat(resposta.getNumero()).isEqualTo(31);
        assertThat(resposta.getLogradouro()).isEqualTo("Dr.João Freitas");
        assertThat(resposta.getComplemento()).isEqualTo("Casa");
        assertThat(resposta.getBairro()).isEqualTo("Brotas");
        assertThat(resposta.getLocalidade()).isEqualTo("Salvador");
        assertThat(resposta.getUf()).isEqualTo("BA");
        assertThat(resposta.getDestinatario()).isEqualTo("Pedro Henrique");
    }


    @Test
    void deletar_sucesso() {
        when(clienteRepository.findById(ClienteMock.LONG_ID)).thenReturn(Optional.ofNullable(ClienteMock.getCliente()));
        when(enderecoRepository.findById(anyLong())).thenReturn(Optional.of(EnderecoMock.getEndereco()));
        enderecoService.deletar(EnderecoMock.LONG_ID, ClienteMock.LONG_ID);

        verify(clienteRepository, times(1)).findById(anyLong());
        verify(clienteRepository, times(1)).save(any());
    }

    @Test
    void deletar_quandoListaEnderecoClientesConterEnderecoUnico_retornaBusinessException() {
        Cliente cliente = ClienteMock.getCliente();
        cliente.getEnderecos().removeIf(e -> e.getId() == 2);

        when(clienteRepository.findById(ClienteMock.LONG_ID)).thenReturn(Optional.of(cliente));

        var exception = assertThrows(BusinessException.class, () -> enderecoService.deletar(EnderecoMock.LONG_ID, ClienteMock.LONG_ID));
        assertThat(exception.getMessage()).isEqualTo("O endereço não pode ser deletado, pois há somente um disponível");
    }

    @Test
    void deletar_quandoClienteNaoEncontrado_retornaBusinessException() {
        var exception = assertThrows(BusinessException.class, () -> enderecoService.deletar(EnderecoMock.LONG_ID, ClienteMock.LONG_ID));
        assertThat(exception.getMessage()).isEqualTo("Cliente não encontrado");

    }

}
