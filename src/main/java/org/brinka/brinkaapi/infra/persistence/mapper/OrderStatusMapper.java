package org.brinka.brinkaapi.infra.persistence.mapper;

import org.brinka.brinkaapi.domain.model.OrderStatus;
import org.brinka.brinkaapi.infra.persistence.entity.OrderStatusEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderStatusMapper {
    OrderStatus toDomain(OrderStatusEntity entity);
    OrderStatusEntity toEntity(OrderStatus domain);
}
