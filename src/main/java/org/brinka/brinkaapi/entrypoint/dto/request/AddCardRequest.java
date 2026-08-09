package org.brinka.brinkaapi.entrypoint.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;


public record AddCardRequest(
        @NotBlank(message = "obrigatório")
        String numero_cartao,
        @NotBlank(message = "obrigatório")
        String nome_titular,
        @NotNull(message = "obrigatório")
        @Future(message = "não pode ser expirado")
        LocalDate data_validade,
        @NotBlank(message = "obrigatório")
        @Pattern(regexp = "^\\d{3}$", message = "deve conter exatamente três números")
        String cvc
) {
}
