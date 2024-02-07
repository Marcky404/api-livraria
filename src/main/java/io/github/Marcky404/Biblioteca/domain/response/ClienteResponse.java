package io.github.Marcky404.Biblioteca.domain.response;

import io.github.Marcky404.Biblioteca.domain.Cliente;
import io.github.Marcky404.Biblioteca.domain.Endereco;
import io.github.Marcky404.Biblioteca.domain.Telefone;
import io.github.Marcky404.Biblioteca.domain.enums.Sexo;
import io.github.Marcky404.Biblioteca.domain.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static io.github.Marcky404.Biblioteca.domain.request.EnderecoRequest.converterEnderecoParaParaLista;
import static io.github.Marcky404.Biblioteca.domain.request.TelefoneRequest.converterParaLista;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse {

    private String nome;
    private String sobrenome;
    private String cpf;
    private Status status;
    private String email;
    private Sexo sexo;
    private LocalDate dataNascimento;


    public ClienteResponse converterParaEntidade(Cliente cliente) {

        nome = cliente.getNome();
        sobrenome = cliente.getSobrenome();
        cpf = cliente.getCpf();
        status = cliente.getStatus();
        email = cliente.getEmail();
        sexo = cliente.getSexo();
        dataNascimento = cliente.getDataNascimento();
        return  new ClienteResponse(nome,sobrenome,cpf,status,email,sexo,dataNascimento);
    }
}
