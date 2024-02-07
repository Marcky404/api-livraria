package io.github.Marcky404.Biblioteca.domain.request;

import io.github.Marcky404.Biblioteca.domain.Cliente;
import io.github.Marcky404.Biblioteca.domain.Endereco;
import io.github.Marcky404.Biblioteca.domain.Telefone;
import io.github.Marcky404.Biblioteca.domain.enums.Sexo;
import io.github.Marcky404.Biblioteca.domain.enums.Status;
import io.github.Marcky404.Biblioteca.utils.Utils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

import static io.github.Marcky404.Biblioteca.domain.request.EnderecoRequest.converterEnderecoParaParaLista;
import static io.github.Marcky404.Biblioteca.domain.request.TelefoneRequest.converterParaLista;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

    @NotBlank(message = "Campo NOME não pode ser vazio ou nulo")
    private String nome;
    @NotBlank(message = "Campo SOBRENOME não pode ser vazio ou nulo")
    private String sobrenome;
    @CPF
    private String cpf;
    private Status status;
    @NotNull
    @Pattern(regexp = "^[\\w\\-]+(\\.[\\w\\-]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$", message="E-mail com formato incorreto.")
    private String email;
    private Sexo sexo;
    private LocalDate dataNascimento;
    @NotNull(message = "Campo telephones não pode estar vazio")
    @Size(min = 1, max = 3, message = "Campo Telefone deve conter 1 até 3 telefones")
    @Valid
    private List<TelefoneRequest> telefones;
    @NotNull(message = "Campo endereco não pode estar vazio")
    @Size(min = 1, max = 3, message = "Campo endereço deve conter 1 até 3 enderecos")
    @Valid
    private List<EnderecoRequest> enderecos;


    public Cliente converterParaEntidade() {
        List<Telefone> telefones = converterParaLista(this.telefones);
        List<Endereco> enderecos = converterEnderecoParaParaLista(this.enderecos);
        return new Cliente(nome, sobrenome, cpf, status, email, sexo, dataNascimento, telefones, enderecos);
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome.toUpperCase();
    }

    public void setCpf(String cpf) {
        this.cpf = Utils.mascararCpf(cpf);
    }
}
