package org.brinka.brinkaapi.infra.persistence.mapper;

import org.brinka.brinkaapi.domain.model.Payment;
import org.brinka.brinkaapi.infra.persistence.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PaymentTypeMapper.class, PaymentStatusMapper.class})
public interface PaymentMapper {
    Payment toDomain(PaymentEntity entity);

    @Mapping(target = "pedido", ignore = true)
    PaymentEntity toEntity(Payment domain);
}
