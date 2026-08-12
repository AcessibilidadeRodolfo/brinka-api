package org.brinka.brinkaapi.infra.persistence.mapper;

import org.brinka.brinkaapi.domain.model.OrderItem;
import org.brinka.brinkaapi.domain.model.Product;
import org.brinka.brinkaapi.infra.persistence.entity.OrderItemEntity;
import org.brinka.brinkaapi.infra.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface OrderItemMapper {

    @Mapping(source = "boneco", target = "produto")
    OrderItem toDomain(OrderItemEntity entity);

    @Mapping(source = "produto", target = "boneco")
    @Mapping(target = "pedido", ignore = true)
    OrderItemEntity toEntity(OrderItem domain);

    @Mapping(target = "avaliacoes", ignore = true)
    Product toDomain(ProductEntity entity);

    @Mapping(target = "avaliacoes", ignore = true)
    ProductEntity toEntity(Product product);
}
