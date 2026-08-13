package org.brinka.brinkaapi.entrypoint.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;


public record AddCardRequest(
        @NotBlank(message = "obrigatório")
        String numeroCartao,
        @NotBlank(message = "obrigatório")
        String nomeTitular,
        @NotNull(message = "obrigatório")
        @Future(message = "não pode ser expirado")
        LocalDate dataValidade,
        @NotBlank(message = "obrigatório")
        @Pattern(regexp = "^\\d{3}$", message = "deve conter exatamente três números")
        String cvc
) {
}
