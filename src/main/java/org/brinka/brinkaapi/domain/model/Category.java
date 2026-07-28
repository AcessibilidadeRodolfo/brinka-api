package org.brinka.brinkaapi.domain.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Category {
    private Integer id;
    private String descricao;
}
