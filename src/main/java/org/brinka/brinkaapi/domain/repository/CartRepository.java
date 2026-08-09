package org.brinka.brinkaapi.domain.repository;

import org.brinka.brinkaapi.domain.model.Cart;

public interface CartRepository {
    Cart getCartByUserId(Integer userId);
    Cart saveCart(Cart cart);
}
