package org.brinka.brinkaapi.application.dto;

public record EnderecoInput(
        String cep,
        String rua,
        Integer numero,
        String complemento,
        String cidade,
        String estado
) {
}