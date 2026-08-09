package org.brinka.brinkaapi.entrypoint.dto.response;

import java.math.BigDecimal;

public record CartItemResponse(
        Integer productId,
        String nome,
        String imagem,
        BigDecimal preco,
        Integer quantidade
) {}