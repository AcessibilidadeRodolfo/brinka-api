package org.brinka.brinkaapi.application.dto.input;

import java.util.Date;

public record CardInput(
        String numeroCartao,
        String nomeTitular,
        Date dataValidade,
        String cvc
) {
}
