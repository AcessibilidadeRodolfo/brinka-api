package org.brinka.brinkaapi.infra.persistence.repository;

import org.brinka.brinkaapi.infra.persistence.entity.PaymentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaPaymentTypeRepository extends JpaRepository<PaymentTypeEntity, Integer> {
    Optional<PaymentTypeEntity> findByDescricao(String descricao);
}
