package org.brinka.brinkaapi.infra.persistence.mapper;

import org.brinka.brinkaapi.domain.model.User;
import org.brinka.brinkaapi.infra.persistence.entity.UserEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        uses = {
                AddressMapper.class,
                ReviewMapper.class
        }
)
public interface UserMapper {
    @Mapping(target = "avaliacoes", ignore = true)
    UserEntity toEntity(User domain);
    @Mapping(target = "avaliacoes", ignore = true)
    User toDomain(UserEntity entity);

    @AfterMapping
    default void setAddressUsuario(@MappingTarget UserEntity user) {
        if (user.getAddress() != null) {
            user.getAddress().setUsuario(user);
        }
    }
}
