package org.brinka.brinkaapi.infra.persistence.mapper;

import org.brinka.brinkaapi.domain.model.Address;
import org.brinka.brinkaapi.infra.persistence.entity.AddressEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface AddressMapper {
    AddressEntity toEntity(Address domain);
    Address toDomain(AddressEntity entity);
}
