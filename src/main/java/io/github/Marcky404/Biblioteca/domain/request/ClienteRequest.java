package io.github.Marcky404.Biblioteca.domain.request;

import io.github.Marcky404.Biblioteca.domain.Endereco;
import io.github.Marcky404.Biblioteca.domain.Telefone;
import io.github.Marcky404.Biblioteca.domain.enuns.Sexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {


    private String nome;
    private String sobrenome;
    private String cpf;
    private Sexo sexo;
    private LocalDate dataNascimento;
    private List<Telefone> telefones;
    private List<Endereco> enderecos;
}
