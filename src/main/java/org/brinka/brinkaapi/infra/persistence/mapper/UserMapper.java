package org.brinka.brinkaapi.infra.persistence.mapper;

import org.brinka.brinkaapi.domain.model.User;
import org.brinka.brinkaapi.infra.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(User domain);
    User toDomain(UserEntity entity);
}
