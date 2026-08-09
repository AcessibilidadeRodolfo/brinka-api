package org.brinka.brinkaapi.entrypoint.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoRequest(

        @NotBlank(message = "obrigatório")
        @Pattern(
                regexp = "^\\d{5}-?\\d{3}",
                message = "sintaxe inválida"
        )
        String cep,

        @NotBlank(message = "obrigatório")
        String rua,

        @NotBlank(message = "obrigatório")
        String numero,

        String complemento,

        @NotBlank(message = "obrigatório")
        String cidade,

        @NotBlank(message = "obrigatório")
        String estado

) {
}