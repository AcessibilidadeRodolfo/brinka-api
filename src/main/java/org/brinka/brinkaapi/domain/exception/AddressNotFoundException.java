package org.brinka.brinkaapi.domain.exception;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(String user) {
        super(String.format("Endereço não encontrado para o usuário: %s", user));
    }
}
