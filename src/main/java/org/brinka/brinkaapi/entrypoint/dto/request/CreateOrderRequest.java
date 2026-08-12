package org.brinka.brinkaapi.entrypoint.dto.request;

import jakarta.validation.constraints.NotNull;
import org.brinka.brinkaapi.domain.enums.PaymentMethod;

public record CreateOrderRequest(
        @NotNull(message = "obrigatório")
        PaymentMethod metodoPagamento
) {
}
