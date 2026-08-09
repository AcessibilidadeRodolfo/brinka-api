package org.brinka.brinkaapi.application.dto.input;

public record SignUpUseCaseInput(
        String nome,
        String telefone,
        String email,
        String senha,
        AddressInput address
) {
}