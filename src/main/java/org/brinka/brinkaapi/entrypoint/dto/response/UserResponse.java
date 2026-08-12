package org.brinka.brinkaapi.entrypoint.dto.response;

public record UserResponse(
        Integer id,
        String nome,
        String email,
        String telefone
) {
}
