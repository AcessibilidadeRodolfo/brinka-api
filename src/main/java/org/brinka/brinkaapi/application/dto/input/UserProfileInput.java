package org.brinka.brinkaapi.application.dto.input;

public record UserProfileInput(
        String email,
        String nome,
        String telefone
) {
}
