package org.brinka.brinkaapi.application.dto;

import java.math.BigDecimal;

public record AddProductUseCaseInput(
        String imagem,
        String nome,
        Integer categoryId,
        String descricao,
        BigDecimal preco,
        Integer estoque
) {
}
