package org.brinka.brinkaapi.entrypoint.dto.response;

import org.brinka.brinkaapi.domain.model.Product;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponse(
        Integer id,
        String imagem,
        String nome,
        String categoria,
        String descricao,
        BigDecimal preco,
        List<ReviewResponse> avaliacoes,
        Integer estoque
) {

}
