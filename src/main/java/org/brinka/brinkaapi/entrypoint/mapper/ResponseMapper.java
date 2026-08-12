package org.brinka.brinkaapi.entrypoint.mapper;

import org.brinka.brinkaapi.application.dto.output.CartItemOutput;
import org.brinka.brinkaapi.application.dto.output.CartOutput;
import org.brinka.brinkaapi.domain.model.Card;
import org.brinka.brinkaapi.domain.model.Address;
import org.brinka.brinkaapi.domain.model.Order;
import org.brinka.brinkaapi.domain.model.OrderItem;
import org.brinka.brinkaapi.domain.model.Payment;
import org.brinka.brinkaapi.domain.model.Product;
import org.brinka.brinkaapi.domain.model.Review;
import org.brinka.brinkaapi.entrypoint.dto.response.CardResponse;
import org.brinka.brinkaapi.entrypoint.dto.response.AddressResponse;
import org.brinka.brinkaapi.entrypoint.dto.response.CartResponse;
import org.brinka.brinkaapi.entrypoint.dto.response.ProductResponse;
import org.brinka.brinkaapi.entrypoint.dto.response.*;
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

    @Mapping(source = "usuario.nome", target = "usuario")
    ReviewResponse toResponse(Review review);

    // Address Responses
    AddressResponse toResponse(Address domain);

    // Cart Responses
    CartResponse toResponse(CartOutput domain);
    CartItemResponse toResponse(CartItemOutput output);

    // Order Responses
    @Mapping(source = "status.descricao", target = "status")
    OrderResponse toResponse(Order order);
    List<OrderResponse> toOrderResponseList(List<Order> orders);

    @Mapping(source = "produto.id", target = "productId")
    @Mapping(source = "produto.nome", target = "nome")
    @Mapping(source = "produto.imagem", target = "imagem")
    OrderItemResponse toResponse(OrderItem item);

    @Mapping(source = "tipo.descricao", target = "tipo")
    @Mapping(source = "status.descricao", target = "status")
    OrderPaymentResponse toResponse(Payment payment);
}
