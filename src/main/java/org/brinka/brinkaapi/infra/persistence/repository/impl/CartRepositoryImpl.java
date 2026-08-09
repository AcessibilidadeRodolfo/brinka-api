package org.brinka.brinkaapi.infra.persistence.repository.impl;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.domain.model.Cart;
import org.brinka.brinkaapi.domain.model.CartItem;
import org.brinka.brinkaapi.domain.repository.CartRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepository {
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String PRODUCT_PREFIX = "product:";
    private static final String CART_PREFIX = "cart:";

    @Override
    public Cart getCartByUserId(Integer userId) {
        String key = getCartKey(userId);

        var entries = redisTemplate.opsForHash().entries(key);

        var cart = Cart.builder().userId(userId).build();
        var cartItems = new ArrayList<CartItem>();

        entries.forEach((k, v) -> {
            Integer productId = Integer.parseInt(k.toString().substring(PRODUCT_PREFIX.length()));
            Integer quantity = (Integer) v;

            cartItems.add(CartItem.builder()
                    .productId(productId)
                    .quantity(quantity)
                    .build()
            );
        });

        cart.setItems(cartItems);

        return cart;
    }

    @Override
    public Cart saveCart(Cart cart) {
        String key = getCartKey(cart.getUserId());

        redisTemplate.delete(key);

        cart.getItems().forEach(item ->
                redisTemplate.opsForHash().put(
                        key,
                        PRODUCT_PREFIX + item.getProductId(),
                        item.getQuantity()
                )
        );

        return cart;
    }

    private String getCartKey(Integer userId) {
        return String.format("%s%d", CART_PREFIX, userId);
    }
}
