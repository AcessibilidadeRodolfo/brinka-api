package org.brinka.brinkaapi.application.dto;

import java.math.BigDecimal;

public record UpdateProductUseCaseInput(
        Integer id,
        String imagem,
        String nome,
        Integer categoriaId,
        String descricao,
        BigDecimal preco,
        Integer estoque
) {
}
