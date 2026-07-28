package org.brinka.brinkaapi.entrypoint.dto.response;

import org.brinka.brinkaapi.domain.model.Product;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String imagem,
        String nome,
        String categoria,
        String descricao,
        BigDecimal preco,
        Integer estoque
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getImagem(),
                product.getNome(),
                product.getCategoria().getDescricao(),
                product.getDescricao(),
                product.getPreco(),
                product.getEstoque()
        );
    }
}
