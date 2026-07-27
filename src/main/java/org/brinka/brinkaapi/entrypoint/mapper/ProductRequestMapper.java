package org.brinka.brinkaapi.entrypoint.mapper;

import org.brinka.brinkaapi.application.dto.AddProductUseCaseInput;
import org.brinka.brinkaapi.application.dto.UpdateProductUseCaseInput;
import org.brinka.brinkaapi.entrypoint.dto.request.PatchProductRequest;
import org.brinka.brinkaapi.entrypoint.dto.request.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductRequestMapper {
    @Mapping(target = "id", source = "id")
    UpdateProductUseCaseInput toInput(Integer id, PatchProductRequest request);
    AddProductUseCaseInput toInput(ProductRequest request);
    List<AddProductUseCaseInput> toInputList(List<ProductRequest> requests);
}
