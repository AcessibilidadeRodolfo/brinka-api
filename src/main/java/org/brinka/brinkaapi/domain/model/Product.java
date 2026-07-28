package org.brinka.brinkaapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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
