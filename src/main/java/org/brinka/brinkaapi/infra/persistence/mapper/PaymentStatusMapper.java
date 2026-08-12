package org.brinka.brinkaapi.infra.persistence.mapper;

import org.brinka.brinkaapi.domain.model.PaymentStatus;
import org.brinka.brinkaapi.infra.persistence.entity.PaymentStatusEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentStatusMapper {
    PaymentStatus toDomain(PaymentStatusEntity entity);
    PaymentStatusEntity toEntity(PaymentStatus domain);
}
