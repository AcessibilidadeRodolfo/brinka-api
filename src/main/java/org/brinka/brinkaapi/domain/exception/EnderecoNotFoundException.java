package org.brinka.brinkaapi.domain.exception;

public class EnderecoNotFoundException extends RuntimeException {
    public EnderecoNotFoundException(String user) {
        super(String.format("Endereço não encontrado para o usuário: %s", user));
    }
}
