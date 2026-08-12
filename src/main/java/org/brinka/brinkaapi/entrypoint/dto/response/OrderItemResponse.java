package org.brinka.brinkaapi.entrypoint.dto.response;

import java.math.BigDecimal;

public record OrderItemResponse(
        Integer productId,
        String nome,
        String imagem,
        Integer quantidade,
        BigDecimal precoUnitario,
        BigDecimal subtotal
) {
}
