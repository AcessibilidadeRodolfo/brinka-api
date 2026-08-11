package org.brinka.brinkaapi.infra.persistence.mapper;

import org.brinka.brinkaapi.domain.model.Product;
import org.brinka.brinkaapi.domain.model.Review;
import org.brinka.brinkaapi.infra.persistence.entity.ProductEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        uses = {
                CategoryMapper.class,
                ReviewMapper.class
        }
)
public interface ProductMapper {
    @Mapping(target = "avaliacoes", ignore = true)
    Product toDomain(ProductEntity entity);
    Product toDomainWithReviews(ProductEntity entity);
    ProductEntity toEntity(Product product);

    @AfterMapping
    default void setReviewProduct(@MappingTarget ProductEntity product) {
        if (product.getAvaliacoes() != null) {
            product.getAvaliacoes()
                    .forEach(review -> review.setProduto(product));
        }
    }
}
