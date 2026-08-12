package org.brinka.brinkaapi.domain.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Integer id) {
        super("Pedido não encontrado com id: " + id);
    }
}
