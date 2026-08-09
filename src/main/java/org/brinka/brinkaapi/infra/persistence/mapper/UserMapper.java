package org.brinka.brinkaapi.infra.persistence.mapper;

import org.brinka.brinkaapi.domain.model.User;
import org.brinka.brinkaapi.infra.persistence.entity.UserEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = EnderecoMapper.class)
public interface UserMapper {
    UserEntity toEntity(User domain);
    User toDomain(UserEntity entity);

    @AfterMapping
    default void setEnderecoUsuario(@MappingTarget UserEntity user) {
        if (user.getEndereco() != null) {
            user.getEndereco().setUsuario(user);
        }
    }
}
