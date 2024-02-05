package io.github.Marcky404.Biblioteca.domain.request;

import io.github.Marcky404.Biblioteca.domain.Telefone;
import io.github.Marcky404.Biblioteca.domain.enums.TipoTelefone;
import io.github.Marcky404.Biblioteca.utils.Utils;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneRequest {

    @NotNull(message = "O ddd não pode ser nulo")
    private Integer ddd;
    @NotBlank(message = "Campo NÙMERO não pode ser vazio ou nulo")
    private String numero;
    private TipoTelefone tipoTelefone;

    public Telefone converterParaEntidade(){
        return new Telefone(ddd,numero,tipoTelefone);
    }

    public static List<Telefone> converterParaLista(List<TelefoneRequest> telefoneRequestList){
        return telefoneRequestList.stream().map(t -> new Telefone(t.ddd,t.numero,t.tipoTelefone)).toList();
    }
    public void setDdd(Integer ddd) {
        this.ddd = Utils.validarDdd(ddd);
    }
}
