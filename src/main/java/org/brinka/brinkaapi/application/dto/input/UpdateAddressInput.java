package org.brinka.brinkaapi.application.dto.input;

record UpdateAddressInput(
        String cep,
        String rua,
        String numero,
        String complemento,
        String cidade,
        String estado
) {
}
