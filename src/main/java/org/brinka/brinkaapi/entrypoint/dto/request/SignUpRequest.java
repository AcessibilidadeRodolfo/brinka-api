package org.brinka.brinkaapi.entrypoint.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignUpRequest(

        @NotBlank(message = "obrigatório")
        String nome,

        @NotBlank(message = "obrigatório")
        @Pattern(
                regexp = "^(55)?[0-9]{2}9[0-9]{8}$",
                message = "sintaxe inválida"
        )
        String telefone,

        @NotBlank(message = "obrigatório")
        @Email(message = "sintaxe inválida")
        String email,

        @Valid
        EnderecoRequest endereco,

        @NotBlank(message = "obrigatória")
        @Size(
                min = 8,
                message = "deve ter pelo menos 8 caracteres"
        )
        @Pattern(
                regexp = ".*[A-Z].*",
                message = "deve conter ao menos uma letra maiúscula"
        )
        @Pattern(
                regexp = ".*[a-z].*",
                message = "deve conter ao menos uma letra minúscula"
        )
        @Pattern(
                regexp = ".*\\d.*",
                message = "deve conter ao menos um número"
        )
        @Pattern(
                regexp = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*",
                message = "deve conter ao menos um caractere especial"
        )
        String senha

) {
}