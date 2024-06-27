package io.github.Marcky404.Biblioteca.service;


import io.github.Marcky404.Biblioteca.domain.Cliente;
import io.github.Marcky404.Biblioteca.domain.ClienteMock;
import io.github.Marcky404.Biblioteca.domain.Telefone;
import io.github.Marcky404.Biblioteca.domain.TelefoneMock;
import io.github.Marcky404.Biblioteca.domain.response.TelefoneResponse;
import io.github.Marcky404.Biblioteca.exception.BusinessException;
import io.github.Marcky404.Biblioteca.repository.ClienteRepository;
import io.github.Marcky404.Biblioteca.repository.TelefoneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TelefoneServiceTest {

    @InjectMocks
    TelefoneService telefoneService;

    @Mock
    TelefoneRepository telefoneRepository;

    @Mock
    ClienteRepository clienteRepository;


    @Test
    void buscar_Sucesso() {
        when(telefoneRepository.findById(TelefoneMock.LONG_ID)).thenReturn(Optional.ofNullable(TelefoneMock.getTelefone()));
        Telefone resposta = telefoneService.buscar(TelefoneMock.LONG_ID);
        assertThat(resposta.getId()).isEqualTo(TelefoneMock.getTelefone().getId());
        assertThat(resposta.getDdd()).isEqualTo(TelefoneMock.getTelefone().getDdd());
        assertThat(resposta.getNumero()).isEqualTo(TelefoneMock.getTelefone().getNumero());
        assertThat(resposta.getTipoTelefone()).isEqualTo(TelefoneMock.getTelefone().getTipoTelefone());

    }

    @Test
    void buscar_quandoTelefoneNaoLocalizado_retornaBusinessException() {
        var exception = assertThrows(BusinessException.class, () -> telefoneService.buscar(TelefoneMock.LONG_ID));
        assertThat(exception.getMessage()).isEqualTo("Telefone não encontrado");
    }

    @Test
    void atualizar_Sucesso() {
        when(telefoneRepository.findById(TelefoneMock.LONG_ID)).thenReturn(Optional.ofNullable(TelefoneMock.getTelefone()));
        TelefoneResponse resposta = telefoneService.atualizar(TelefoneMock.LONG_ID, TelefoneMock.getTelefoneRequest());

        assertThat(resposta.getDdd()).isEqualTo(75);
        assertThat(resposta.getNumero()).isEqualTo("81889999");
        assertThat(resposta.getTipoTelefone()).isEqualTo(TelefoneMock.getTelefone().getTipoTelefone());
    }

    @Test
    void deletar_Sucesso() {
        when(clienteRepository.findById(ClienteMock.LONG_ID)).thenReturn(Optional.ofNullable(ClienteMock.getCliente()));
        when(telefoneRepository.findById(anyLong())).thenReturn(Optional.of(TelefoneMock.getTelefone()));
        telefoneService.deletar(TelefoneMock.LONG_ID, ClienteMock.LONG_ID);

        verify(clienteRepository, times(1)).findById(anyLong());
        verify(clienteRepository, times(1)).save(any());
    }

    @Test
    void deletar_quandoListaTelefoneClientesConterTelefoneUnico_retornaBusinessException() {
        Cliente cliente = ClienteMock.getCliente();
        cliente.getTelefones().removeIf(e -> e.getId() == 2);

        when(clienteRepository.findById(ClienteMock.LONG_ID)).thenReturn(Optional.of(cliente));

        var exception = assertThrows(BusinessException.class, () -> telefoneService.deletar(TelefoneMock.LONG_ID, ClienteMock.LONG_ID));
        assertThat(exception.getMessage()).isEqualTo("O telefone não pode ser deletado, pois há somente um contato disponível");
    }

    @Test
    void deletar_quandoClienteNaoEncontrado_retornaBusinessException(){
        var exception = assertThrows(BusinessException.class, () -> telefoneService.deletar(TelefoneMock.LONG_ID, ClienteMock.LONG_ID));
        assertThat(exception.getMessage()).isEqualTo("Cliente não encontrado");

    }


}
