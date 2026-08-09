package org.brinka.brinkaapi.entrypoint.mapper;

import org.brinka.brinkaapi.domain.model.Card;
import org.brinka.brinkaapi.domain.model.Address;
import org.brinka.brinkaapi.domain.model.Cart;
import org.brinka.brinkaapi.domain.model.Product;
import org.brinka.brinkaapi.entrypoint.dto.response.CardResponse;
import org.brinka.brinkaapi.entrypoint.dto.response.AddressResponse;
import org.brinka.brinkaapi.entrypoint.dto.response.CartResponse;
import org.brinka.brinkaapi.entrypoint.dto.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResponseMapper {
    // Card Responses
    CardResponse toResponse(Card domain);

    // Product Responses
    @Mapping(source = "categoria.descricao", target = "categoria")
    ProductResponse toResponse(Product product);
    List<ProductResponse> toResponseList(List<Product> products);

    // Address Responses
    AddressResponse toResponse(Address domain);

    // Cart Responses
    CartResponse toResponse(Cart domain);
}
