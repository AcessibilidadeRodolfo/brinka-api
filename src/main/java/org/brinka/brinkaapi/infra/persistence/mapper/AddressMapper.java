package org.brinka.brinkaapi.infra.persistence.mapper;

import org.brinka.brinkaapi.domain.model.Address;
import org.brinka.brinkaapi.infra.persistence.entity.AddressEntity;
import org.brinka.brinkaapi.infra.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface AddressMapper {
    @Mapping(target = "usuario", source = "idUsuario", qualifiedByName = "idToUserEntityId")
    AddressEntity toEntity(Address domain);
    @Mapping(target = "idUsuario", source = "usuario.id")
    Address toDomain(AddressEntity entity);

    @Named("idToUserEntityId")
    default UserEntity idToUserEntityId(Integer idUsuario) {
        if (idUsuario == null) return null;
        UserEntity ref = new UserEntity();
        ref.setId(idUsuario);
        return ref;
    }
}
