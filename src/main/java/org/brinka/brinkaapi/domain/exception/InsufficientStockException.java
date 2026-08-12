package org.brinka.brinkaapi.domain.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(Integer productId) {
        super(String.format("Estoque insuficiente para o produto com id '%d'", productId));
    }
}
