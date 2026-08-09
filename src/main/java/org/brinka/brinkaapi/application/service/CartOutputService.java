package org.brinka.brinkaapi.application.service;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.dto.output.CartItemOutput;
import org.brinka.brinkaapi.application.dto.output.CartOutput;
import org.brinka.brinkaapi.domain.exception.ProductNotFoundException;
import org.brinka.brinkaapi.domain.model.Cart;
import org.brinka.brinkaapi.domain.model.CartItem;
import org.brinka.brinkaapi.domain.model.Product;
import org.brinka.brinkaapi.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartOutputService {
    private final ProductRepository productRepository;

    public CartOutput buildOutput(Cart cart) {

        var productIds = cart.getItems().stream()
                .map(CartItem::getProductId)
                .toList();

        var products = productRepository.findProductsById(productIds);

        var productsById = products.stream()
                .collect(Collectors.toMap(
                        Product::getId,
                        product -> product
                ));

        var cartItemsOutput = cart.getItems().stream()
                .map(item -> {
                    var product = productsById.get(item.getProductId());

                    if (product == null) {
                        throw new ProductNotFoundException(item.getProductId());
                    }

                    return CartItemOutput.builder()
                            .productId(item.getProductId())
                            .nome(product.getNome())
                            .imagem(product.getImagem())
                            .preco(product.getPreco())
                            .quantidade(item.getQuantity())
                            .build();
                })
                .toList();

        var total = cartItemsOutput.stream()
                .map(item -> item.preco().multiply(BigDecimal.valueOf(item.quantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return CartOutput.builder()
                .items(cartItemsOutput)
                .total(total)
                .build();
    }
}
