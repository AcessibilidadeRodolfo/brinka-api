package org.brinka.brinkaapi.application.dto;

public record SignUpUseCaseInput(
        String nome,
        String telefone,
        String email,
        String senha,
        EnderecoInput endereco
) {
}