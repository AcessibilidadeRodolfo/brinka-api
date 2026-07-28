package org.brinka.brinkaapi.infra.persistence.mapper;

import org.brinka.brinkaapi.domain.model.Category;
import org.brinka.brinkaapi.infra.persistence.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toDomain(CategoryEntity entity);

    CategoryEntity toEntity(Category categoria);
}
