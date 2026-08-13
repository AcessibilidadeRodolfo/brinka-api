package org.brinka.brinkaapi.entrypoint.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

public record UpdateCardRequest(
        String numeroCartao,
        String nomeTitular,
        @Future(message = "não pode ser expirado")
        Date dataValidade,
        @Pattern(regexp = "^\\d{3}$", message = "deve conter exatamente três números")
        String cvc
) {
}
