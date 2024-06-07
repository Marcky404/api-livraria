package io.github.Marcky404.Biblioteca.domain.response;

import io.github.Marcky404.Biblioteca.domain.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResponse {

    @Schema(title = "CEP", example = "40000-000")
    private String cep;

    @Schema(title = "Número", example = "123")
    private Integer numero;

    @Schema(title = "Logradouro", example = "Dr.João Freitas")
    private String logradouro;

    @Schema(title = "Complemento", example = "Apartamento 101")
    private String complemento;

    @Schema(title = "Bairro", example = "Brotas")
    private String bairro;

    @Schema(title = "Localidade", example = "Campinas")
    private String localidade;

    @Schema(title = "UF", example = "SP")
    private String uf;

    @Schema(title = "Destinatário", example = "João Silva")
    private String destinatario;

    public EnderecoResponse(Endereco endereco) {
        this.cep = endereco.getCep();
        this.numero = endereco.getNumero();
        this.logradouro = endereco.getLogradouro();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.localidade = endereco.getLocalidade();
        this.uf = endereco.getUf();
        this.destinatario = endereco.getDestinatario();
    }
}
