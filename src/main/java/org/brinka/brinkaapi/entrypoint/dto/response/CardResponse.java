package org.brinka.brinkaapi.entrypoint.dto.response;

import java.time.LocalDate;

public record CardResponse(
        Integer id,
        String numeroCartao,
        String nomeTitular,
        String cvc,
        LocalDate dataValidade
) {}