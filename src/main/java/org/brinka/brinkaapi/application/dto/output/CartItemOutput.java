package org.brinka.brinkaapi.application.dto.output;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CartItemOutput(
        Integer productId,
        String nome,
        String imagem,
        BigDecimal preco,
        Integer quantidade
) {
}
