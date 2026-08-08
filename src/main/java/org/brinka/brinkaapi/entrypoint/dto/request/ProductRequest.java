package org.brinka.brinkaapi.entrypoint.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "obrigatória")
        String imagem,

        @NotBlank(message = "obrigatório")
        String nome,

        @NotNull(message = "obrigatório")
        Integer categoriaId,

        @NotBlank(message = "obrigatória")
        String descricao,

        @NotNull(message = "obrigatório")
        @Positive(message = "deve ser maior que zero")
        BigDecimal preco,

        @NotNull(message = "obrigatório")
        @PositiveOrZero(message = "deve ser maior ou igual à zero")
        Integer estoque
) {
}