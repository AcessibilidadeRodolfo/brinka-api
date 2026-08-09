package org.brinka.brinkaapi.application.dto;

record UpdateEnderecoInput(
        String cep,
        String rua,
        String numero,
        String complemento,
        String cidade,
        String estado
) {
}
