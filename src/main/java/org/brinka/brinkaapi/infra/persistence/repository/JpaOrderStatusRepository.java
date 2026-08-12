package org.brinka.brinkaapi.infra.persistence.repository;

import org.brinka.brinkaapi.infra.persistence.entity.OrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaOrderStatusRepository extends JpaRepository<OrderStatusEntity, Integer> {
    Optional<OrderStatusEntity> findByDescricao(String descricao);
}
