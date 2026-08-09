package org.brinka.brinkaapi.entrypoint.dto.request;

import jakarta.validation.constraints.Pattern;

public record UpdateEnderecoRequest(
        @Pattern(
                regexp = "^\\d{5}-?\\d{3}",
                message = "sintaxe inválida"
        )
        String cep,
        String rua,
        String numero,
        String complemento,
        String cidade,
        String estado
) {
}
