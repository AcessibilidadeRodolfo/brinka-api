package org.brinka.brinkaapi.entrypoint.mapper;

import org.brinka.brinkaapi.application.dto.SignUpUseCaseInput;
import org.brinka.brinkaapi.entrypoint.dto.request.SignUpRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SignUpRequestMapper {
    SignUpUseCaseInput toInput(SignUpRequest request);
}
