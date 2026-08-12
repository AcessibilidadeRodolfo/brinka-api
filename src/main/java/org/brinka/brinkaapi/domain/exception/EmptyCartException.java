package org.brinka.brinkaapi.domain.exception;

public class EmptyCartException extends RuntimeException {
    public EmptyCartException() {
        super("Carrinho vazio");
    }
}
