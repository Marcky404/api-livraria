package io.github.Marcky404.Biblioteca.domain.response;

import io.github.Marcky404.Biblioteca.domain.Cliente;
import io.github.Marcky404.Biblioteca.domain.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDesativarResponse {

    @NotNull(message = "Id de cliente não pode ser nulo")
    private Long id;
    @NotNull(message = "Status de cliente não pode ser nulo")
    private Status status;

    public ClienteDesativarResponse(Cliente cliente) {
        this.id = cliente.getId();
        this.status = cliente.getStatus();
    }
}
