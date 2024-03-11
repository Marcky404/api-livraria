package io.github.Marcky404.Biblioteca.domain.request;

import io.github.Marcky404.Biblioteca.domain.Telefone;
import io.github.Marcky404.Biblioteca.domain.enums.TipoTelefone;
import io.github.Marcky404.Biblioteca.utils.Utils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneRequest {

    @NotNull(message = "O DDD não pode ser nulo")
    @Schema(title = "DDD", required = true, example = "11", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer ddd;
    @NotBlank(message = "Campo NÚMERO não pode ser vazio ou nulo")
    @Schema(title = "Número", required = true, example = "999999999", requiredMode = Schema.RequiredMode.REQUIRED)
    private String numero;
    @Schema(title = "Tipo de Telefone", required = true, example = "CELULAR", requiredMode = Schema.RequiredMode.REQUIRED)
    private TipoTelefone tipoTelefone;

    public TelefoneRequest(Telefone telefone) {
        this.ddd = telefone.getDdd();
        this.numero = telefone.getNumero();
        this.tipoTelefone = telefone.getTipoTelefone();
    }

    public static List<Telefone> converterParaLista(List<TelefoneRequest> telefoneRequestList){
        return telefoneRequestList.stream().map(t -> new Telefone(t.ddd,t.numero,t.tipoTelefone)).toList();
    }
    public void setDdd(Integer ddd) {
        this.ddd = Utils.validarDdd(ddd);
    }
}