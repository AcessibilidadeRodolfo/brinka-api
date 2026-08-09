package org.brinka.brinkaapi.entrypoint.mapper;

import org.brinka.brinkaapi.domain.model.Card;
import org.brinka.brinkaapi.entrypoint.dto.response.CardResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardResponseMapper {
    CardResponse toResponse(Card domain);
}
