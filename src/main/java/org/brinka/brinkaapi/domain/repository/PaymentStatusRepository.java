package org.brinka.brinkaapi.domain.repository;

import org.brinka.brinkaapi.domain.model.PaymentStatus;

import java.util.Optional;

public interface PaymentStatusRepository {
    Optional<PaymentStatus> findByDescricao(String descricao);
}
