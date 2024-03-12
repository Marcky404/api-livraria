package io.github.Marcky404.Biblioteca.domain.request;

import io.github.Marcky404.Biblioteca.domain.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnderecoRequest {

    @NotBlank(message = "O campo cep não pode ser vazio ou nulo!")
    @Schema(title = "CEP", example = "40285-001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cep;
    @NotNull(message = "O campo número não pode ser vazio ou nulo!")
    @Schema(title = "Número", example = "123", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer numero;
    @Schema(title = "Logradouro", example = "Dr.João Freitas", requiredMode = Schema.RequiredMode.REQUIRED)
    private String logradouro;
    @Schema(title = "Complemento", example = "Apartamento 101")
    private String complemento;
    @Schema(title = "Bairro", example = "Brotas")
    private String bairro;
    @Schema(title = "Localidade", example = "Salvador")
    private String localidade;
    @Schema(title = "UF", example = "BA")
    private String uf;
    @NotBlank(message = "O campo destinatario não pode ser vazio ou nulo!")
    @Schema(title = "Destinatário", example = "João Silva", requiredMode = Schema.RequiredMode.REQUIRED)
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