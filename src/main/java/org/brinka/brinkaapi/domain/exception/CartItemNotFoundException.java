package org.brinka.brinkaapi.domain.exception;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(Integer productId) {
        super(String.format("Produto com id '%d' não encontrado", productId));
    }
}
