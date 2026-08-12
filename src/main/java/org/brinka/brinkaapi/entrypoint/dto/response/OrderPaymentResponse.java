package org.brinka.brinkaapi.entrypoint.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderPaymentResponse(
        Integer id,
        String tipo,
        String status,
        BigDecimal valor,
        LocalDateTime dataPagamento
) {
}
