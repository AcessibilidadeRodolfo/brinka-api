package org.brinka.brinkaapi.entrypoint.dto.request;

import jakarta.validation.constraints.Pattern;

public record UpdateUserRequest(
        String nome,

        @Pattern(
                regexp = "^(55)?[0-9]{2}9[0-9]{8}$",
                message = "sintaxe inválida"
        )
        String telefone
) {
}
