package org.brinka.brinkaapi.application.usecase.user.cart;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.application.dto.output.CartOutput;
import org.brinka.brinkaapi.application.service.CartOutputService;
import org.brinka.brinkaapi.domain.exception.ProductNotFoundException;
import org.brinka.brinkaapi.domain.exception.UserNotFoundException;
import org.brinka.brinkaapi.domain.model.Cart;
import org.brinka.brinkaapi.domain.repository.CartRepository;
import org.brinka.brinkaapi.domain.repository.ProductRepository;
import org.brinka.brinkaapi.domain.repository.UserRepository;

@UseCase
@RequiredArgsConstructor
public class AddCartItemUseCase {
    private final CartRepository repository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartOutputService cartOutputService;

    public CartOutput execute(Integer productId, String email) {
        var user = userRepository.findUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        productRepository.findProductById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        var cart = repository.getCartByUserId(user.getId());

        cart.addItem(productId);

        return cartOutputService.buildOutput(repository.saveCart(cart));
    }
}
