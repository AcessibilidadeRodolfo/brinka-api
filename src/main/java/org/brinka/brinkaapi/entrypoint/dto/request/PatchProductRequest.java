package org.brinka.brinkaapi.entrypoint.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record PatchProductRequest(
        @Pattern(regexp = ".+", message = "não pode ser vazio") String imagem,
        @Pattern(regexp = ".+", message = "não pode ser vazio") String nome,
        Integer categoriaId,
        @Pattern(regexp = ".+", message = "não pode ser vazio") String descricao,
        @Positive BigDecimal preco,
        @PositiveOrZero Integer estoque
) {
}