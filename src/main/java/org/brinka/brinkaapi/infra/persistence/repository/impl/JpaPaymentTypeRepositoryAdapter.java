package org.brinka.brinkaapi.infra.persistence.repository.impl;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.domain.model.PaymentType;
import org.brinka.brinkaapi.domain.repository.PaymentTypeRepository;
import org.brinka.brinkaapi.infra.persistence.mapper.PaymentTypeMapper;
import org.brinka.brinkaapi.infra.persistence.repository.JpaPaymentTypeRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaPaymentTypeRepositoryAdapter implements PaymentTypeRepository {
    private final JpaPaymentTypeRepository jpaRepository;
    private final PaymentTypeMapper mapper;

    @Override
    public Optional<PaymentType> findByDescricao(String descricao) {
        return jpaRepository.findByDescricao(descricao).map(mapper::toDomain);
    }
}
