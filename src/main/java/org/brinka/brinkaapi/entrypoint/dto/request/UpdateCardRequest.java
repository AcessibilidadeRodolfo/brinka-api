package org.brinka.brinkaapi.entrypoint.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

public record UpdateCardRequest(
        String numero_cartao,
        String nome_titular,
        @Future(message = "não pode ser expirado")
        Date data_validade,
        @Pattern(regexp = "^\\d{3}$", message = "deve conter exatamente três números")
        String cvc
) {
}
