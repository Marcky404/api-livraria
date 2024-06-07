package io.github.Marcky404.Biblioteca.service;

import io.github.Marcky404.Biblioteca.domain.Endereco;
import io.github.Marcky404.Biblioteca.domain.endereco.EnderecoMock;
import io.github.Marcky404.Biblioteca.exception.BusinessException;
import io.github.Marcky404.Biblioteca.repository.EnderecoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTest {

    @InjectMocks
    EnderecoService enderecoService;

    @Mock
    EnderecoRepository enderecoRepository;
    @Mock
    RestTemplate restTemplate;


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
        when(restTemplate.getForObject("https://viacep.com.br/ws/01001000/json/", eq(Endereco.class), (short) 2)).thenReturn(EnderecoMock.getEnderecoViaCep());
        Endereco resposta = enderecoService.buscarEndereco(EnderecoMock.CEP);
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
}
