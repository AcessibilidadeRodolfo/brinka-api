package org.brinka.brinkaapi.domain.exception;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String user) {
        super(String.format("Cartão não encontrado para o usuário: %s", user));
    }
}
