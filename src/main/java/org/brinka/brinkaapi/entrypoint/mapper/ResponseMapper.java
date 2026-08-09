package org.brinka.brinkaapi.entrypoint.mapper;

import org.brinka.brinkaapi.application.dto.output.CartItemOutput;
import org.brinka.brinkaapi.application.dto.output.CartOutput;
import org.brinka.brinkaapi.domain.model.Card;
import org.brinka.brinkaapi.domain.model.Address;
import org.brinka.brinkaapi.domain.model.Cart;
import org.brinka.brinkaapi.domain.model.Product;
<<<<<<< HEAD
import org.brinka.brinkaapi.entrypoint.dto.response.CardResponse;
import org.brinka.brinkaapi.entrypoint.dto.response.AddressResponse;
import org.brinka.brinkaapi.entrypoint.dto.response.CartResponse;
import org.brinka.brinkaapi.entrypoint.dto.response.ProductResponse;
=======
import org.brinka.brinkaapi.entrypoint.dto.response.*;
>>>>>>> e042e64 (fix: adicionar infos do produto as rotas de carrinho)
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
    CartResponse toResponse(CartOutput domain);
    CartItemResponse toResponse(CartItemOutput output);
}
