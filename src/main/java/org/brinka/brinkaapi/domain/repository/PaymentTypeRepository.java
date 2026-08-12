package org.brinka.brinkaapi.domain.repository;

import org.brinka.brinkaapi.domain.model.PaymentType;

import java.util.Optional;

public interface PaymentTypeRepository {
    Optional<PaymentType> findByDescricao(String descricao);
}
