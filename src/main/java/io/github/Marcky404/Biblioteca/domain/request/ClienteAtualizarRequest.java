package io.github.Marcky404.Biblioteca.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(title = "Nome do cliente", example = "João", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;
    @NotBlank(message = "Campo SOBRENOME não pode ser vazio ou nulo")
    @Schema(title = "Sobrenome do cliente", example = "Da Silva", requiredMode = Schema.RequiredMode.REQUIRED)
    private String sobrenome;
    @NotNull
    @Pattern(regexp = "^[\\w\\-]+(\\.[\\w\\-]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$", message = "E-mail com formato incorreto.")
    @Schema(title = "E-mail do cliente", example = "joao@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;


}
