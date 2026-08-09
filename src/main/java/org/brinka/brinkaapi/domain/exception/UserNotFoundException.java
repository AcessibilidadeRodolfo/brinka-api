package org.brinka.brinkaapi.domain.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Usuário atual não encontrado!");
    }
}
