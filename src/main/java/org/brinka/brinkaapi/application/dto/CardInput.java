package org.brinka.brinkaapi.application.dto;

import java.util.Date;

public record CardInput(
        String numeroCartao,
        String nomeTitular,
        Date dataValidade,
        String cvc
) {
}
