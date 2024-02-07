package io.github.Marcky404.Biblioteca.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteAtualizarRequest {


    @NotBlank(message = "Campo NOME não pode ser vazio ou nulo")
    private String nome;
    @NotBlank(message = "Campo SOBRENOME não pode ser vazio ou nulo")
    private String sobrenome;
    @NotNull
    @Pattern(regexp = "^[\\w\\-]+(\\.[\\w\\-]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$", message = "E-mail com formato incorreto.")
    private String email;


}
