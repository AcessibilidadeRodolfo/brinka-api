package org.brinka.brinkaapi.entrypoint.dto.response;

public record EnderecoResponse(
         String cep,
         String rua,
         Integer numero,
         String complemento,
         String cidade,
         String estado
) {
}
