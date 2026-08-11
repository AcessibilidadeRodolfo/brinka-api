package org.brinka.brinkaapi.infra.persistence.mapper;

import org.brinka.brinkaapi.domain.model.Product;
import org.brinka.brinkaapi.domain.model.Review;
import org.brinka.brinkaapi.infra.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {
                CategoryMapper.class,
                ReviewMapper.class
        }
)
public interface ProductMapper {

    Product toDomain(ProductEntity entity);

    ProductEntity toEntity(Product product);
}
