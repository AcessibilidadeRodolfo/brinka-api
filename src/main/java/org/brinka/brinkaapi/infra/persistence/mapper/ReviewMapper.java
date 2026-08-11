package org.brinka.brinkaapi.infra.persistence.mapper;

import org.brinka.brinkaapi.domain.model.Product;
import org.brinka.brinkaapi.domain.model.Review;
import org.brinka.brinkaapi.infra.persistence.entity.ReviewEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface ReviewMapper {
    @Mapping(target = "produto", ignore = true)
    Review toDomain(ReviewEntity entity);
    @Mapping(target = "produto", ignore = true)
    ReviewEntity toEntity(Review domain);
}
