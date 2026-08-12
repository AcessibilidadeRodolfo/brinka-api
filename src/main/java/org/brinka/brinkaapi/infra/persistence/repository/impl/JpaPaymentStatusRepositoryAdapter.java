package org.brinka.brinkaapi.infra.persistence.repository.impl;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.domain.model.PaymentStatus;
import org.brinka.brinkaapi.domain.repository.PaymentStatusRepository;
import org.brinka.brinkaapi.infra.persistence.mapper.PaymentStatusMapper;
import org.brinka.brinkaapi.infra.persistence.repository.JpaPaymentStatusRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaPaymentStatusRepositoryAdapter implements PaymentStatusRepository {
    private final JpaPaymentStatusRepository jpaRepository;
    private final PaymentStatusMapper mapper;

    @Override
    public Optional<PaymentStatus> findByDescricao(String descricao) {
        return jpaRepository.findByDescricao(descricao).map(mapper::toDomain);
    }
}
