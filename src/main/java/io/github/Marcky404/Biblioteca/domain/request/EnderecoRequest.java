package io.github.Marcky404.Biblioteca.domain.request;

import io.github.Marcky404.Biblioteca.domain.Endereco;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnderecoRequest {

    @NotBlank(message = "O campo cep não pode ser vazio ou nulo!")
    private String cep;
    private Integer numero;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    @NotBlank(message = "O campo destinatario não pode ser vazio ou nulo!")
    private String destinatario;

    public EnderecoRequest(Endereco endereco) {
        this.cep = endereco.getCep();
        this.numero = endereco.getNumero();
        this.logradouro = endereco.getLogradouro();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.localidade = endereco.getLocalidade();
        this.uf = endereco.getUf();
        this.destinatario = endereco.getDestinatario();
    }

    public static List<Endereco> converterEnderecoParaParaLista(List<EnderecoRequest> enderecoRequestList) {
        return enderecoRequestList.stream().map(e -> new Endereco(e.cep, e.numero, e.logradouro, e.complemento, e.bairro, e.localidade, e.uf, e.destinatario)).toList();
    }
}
