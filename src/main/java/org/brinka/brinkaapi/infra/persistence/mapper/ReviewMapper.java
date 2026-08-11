package org.brinka.brinkaapi.infra.persistence.mapper;

import org.brinka.brinkaapi.domain.model.Review;
import org.brinka.brinkaapi.infra.persistence.entity.ReviewEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review toDomain(ReviewEntity entity);
    ReviewEntity toEntity(Review domain);
}
