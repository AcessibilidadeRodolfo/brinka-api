package org.brinka.brinkaapi.infra.persistence.repository;

import org.brinka.brinkaapi.infra.persistence.entity.PaymentStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaPaymentStatusRepository extends JpaRepository<PaymentStatusEntity, Integer> {
    Optional<PaymentStatusEntity> findByDescricao(String descricao);
}
