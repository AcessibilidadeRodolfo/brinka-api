package org.brinka.brinkaapi.entrypoint.mapper;

import org.brinka.brinkaapi.domain.model.Product;
import org.brinka.brinkaapi.entrypoint.dto.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductResponseMapper {
    @Mapping(source = "categoria.descricao", target = "categoria")
    ProductResponse toResponse(Product product);

    List<ProductResponse> toResponseList(List<Product> products);
}
