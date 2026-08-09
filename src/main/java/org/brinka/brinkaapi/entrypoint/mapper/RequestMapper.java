package org.brinka.brinkaapi.entrypoint.mapper;

import org.brinka.brinkaapi.application.dto.*;
import org.brinka.brinkaapi.entrypoint.dto.request.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestMapper {
    // Card Requests
    CardInput toInput(UpdateCardRequest request);
    CardInput toInput(AddCardRequest request);

    // Update Requests
    @Mapping(target = "id", source = "id")
    UpdateProductUseCaseInput toInput(Integer id, PatchProductRequest request);
    AddProductUseCaseInput toInput(ProductRequest request);
    List<AddProductUseCaseInput> toInputList(List<ProductRequest> requests);

    // SignUp Requests
    SignUpUseCaseInput toInput(SignUpRequest request);

    // Endereco Requests
    EnderecoInput toInput(UpdateEnderecoRequest request);
}
