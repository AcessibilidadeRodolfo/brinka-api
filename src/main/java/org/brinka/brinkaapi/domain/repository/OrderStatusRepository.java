package org.brinka.brinkaapi.domain.repository;

import org.brinka.brinkaapi.domain.model.OrderStatus;

import java.util.Optional;

public interface OrderStatusRepository {
    Optional<OrderStatus> findByDescricao(String descricao);
}
