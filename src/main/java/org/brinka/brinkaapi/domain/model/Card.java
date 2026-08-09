package org.brinka.brinkaapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class Card {
    private Integer id;
    private User usuario;
    private String numeroCartao;
    private String nomeTitular;
    private Date dataValidade;
    private String cvc;
}
