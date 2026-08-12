package org.brinka.brinkaapi.infra.persistence.mapper;

import org.brinka.brinkaapi.domain.model.Address;
import org.brinka.brinkaapi.infra.persistence.entity.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(target = "usuario", ignore = true)
    AddressEntity toEntity(Address domain);
    @Mapping(target = "usuario", ignore = true)
    Address toDomain(AddressEntity entity);
}
