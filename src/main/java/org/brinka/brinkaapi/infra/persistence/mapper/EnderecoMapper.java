package org.brinka.brinkaapi.infra.persistence.mapper;

import org.brinka.brinkaapi.domain.model.Endereco;
import org.brinka.brinkaapi.infra.persistence.entity.EnderecoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    EnderecoEntity toEntity(Endereco domain);
    Endereco toDomain(EnderecoEntity entity);
}
