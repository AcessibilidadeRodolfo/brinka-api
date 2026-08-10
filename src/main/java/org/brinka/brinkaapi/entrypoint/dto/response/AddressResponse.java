package org.brinka.brinkaapi.entrypoint.dto.response;

public record AddressResponse(
         String cep,
         String rua,
         Integer numero,
         String complemento,
         String cidade,
         String estado
) {
}
