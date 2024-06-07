package io.github.Marcky404.Biblioteca.domain.response;

import io.github.Marcky404.Biblioteca.domain.Telefone;
import io.github.Marcky404.Biblioteca.domain.enums.TipoTelefone;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneResponse {

    @Schema(title = "DDD", example = "71")
    private Integer ddd;

    @Schema(title = "numero", example = "88888888")
    private String numero;

    @Schema(title = "tipoTelefone", example = "CELULAR")
    @Enumerated(EnumType.STRING)
    private TipoTelefone tipoTelefone;


    public TelefoneResponse(Telefone telefone) {
        this.ddd = telefone.getDdd();
        this.numero = telefone.getNumero();
        this.tipoTelefone = telefone.getTipoTelefone();
    }
}
