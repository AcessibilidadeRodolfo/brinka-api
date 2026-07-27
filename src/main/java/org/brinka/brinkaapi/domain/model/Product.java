package org.brinka.brinkaapi.domain.model;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    private Integer id;
    private String imagem;
    private String nome;
    private Category categoria;
    private String descricao;
    private BigDecimal preco;
    private Integer estoque;
}
