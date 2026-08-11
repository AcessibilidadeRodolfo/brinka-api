package org.brinka.brinkaapi.entrypoint.dto.response;

public record ReviewResponse(
        String usuario,
        String comentario,
        Integer nota
) {
}
