package org.brinka.brinkaapi.application.dto.input;

public record AddressInput(
        String cep,
        String rua,
        Integer numero,
        String complemento,
        String cidade,
        String estado
) {
}