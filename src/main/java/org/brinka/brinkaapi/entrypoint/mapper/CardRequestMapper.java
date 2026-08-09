package org.brinka.brinkaapi.entrypoint.mapper;

import org.brinka.brinkaapi.application.dto.CardInput;
import org.brinka.brinkaapi.entrypoint.dto.request.AddCardRequest;
import org.brinka.brinkaapi.entrypoint.dto.request.UpdateCardRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardRequestMapper {
    CardInput toInput(UpdateCardRequest request);
    CardInput toInput(AddCardRequest request);
}
