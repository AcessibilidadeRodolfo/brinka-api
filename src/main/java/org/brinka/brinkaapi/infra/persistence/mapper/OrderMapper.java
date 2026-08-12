package org.brinka.brinkaapi.infra.persistence.mapper;

import org.brinka.brinkaapi.domain.model.Order;
import org.brinka.brinkaapi.infra.persistence.entity.OrderEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        uses = {
                UserMapper.class,
                OrderStatusMapper.class,
                OrderItemMapper.class,
                PaymentMapper.class
        }
)
public interface OrderMapper {
    Order toDomain(OrderEntity entity);
    OrderEntity toEntity(Order domain);

    @AfterMapping
    default void setBackReferences(@MappingTarget OrderEntity order) {
        if (order.getItens() != null) {
            order.getItens().forEach(item -> item.setPedido(order));
        }
        if (order.getPagamento() != null) {
            order.getPagamento().setPedido(order);
        }
    }
}
