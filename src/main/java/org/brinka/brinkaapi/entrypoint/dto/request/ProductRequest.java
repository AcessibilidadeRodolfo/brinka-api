package org.brinka.brinkaapi.entrypoint.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank String imagem,
        @NotBlank String nome,
        @NotNull Integer categoriaId,
        @NotBlank String descricao,
        @NotNull @Positive BigDecimal preco,
        @NotNull @PositiveOrZero Integer estoque
) {
}