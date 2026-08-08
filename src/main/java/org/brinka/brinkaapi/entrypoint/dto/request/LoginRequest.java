package org.brinka.brinkaapi.entrypoint.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "obrigatório")
        @Email(message = "sintaxe inválida")
        String email,

        @NotBlank(message = "obrigatória")
        String senha
) {
}
