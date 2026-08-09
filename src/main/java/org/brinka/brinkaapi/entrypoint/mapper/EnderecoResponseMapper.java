package org.brinka.brinkaapi.entrypoint.mapper;

import org.brinka.brinkaapi.domain.model.Endereco;
import org.brinka.brinkaapi.entrypoint.dto.response.EnderecoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoResponseMapper {
    EnderecoResponse toResponse(Endereco domain);
}
