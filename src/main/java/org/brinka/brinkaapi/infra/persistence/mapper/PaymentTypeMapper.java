package org.brinka.brinkaapi.infra.persistence.mapper;

import org.brinka.brinkaapi.domain.model.PaymentType;
import org.brinka.brinkaapi.infra.persistence.entity.PaymentTypeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentTypeMapper {
    PaymentType toDomain(PaymentTypeEntity entity);
    PaymentTypeEntity toEntity(PaymentType domain);
}
