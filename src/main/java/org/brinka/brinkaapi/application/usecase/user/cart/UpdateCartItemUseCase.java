package org.brinka.brinkaapi.application.usecase.user.cart;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.application.dto.output.CartOutput;
import org.brinka.brinkaapi.application.service.CartOutputService;
import org.brinka.brinkaapi.domain.enums.CartOperation;
import org.brinka.brinkaapi.domain.exception.UserNotFoundException;
import org.brinka.brinkaapi.domain.repository.CartRepository;
import org.brinka.brinkaapi.domain.repository.UserRepository;

@UseCase
@RequiredArgsConstructor
public class UpdateCartItemUseCase {
    private final CartRepository repository;
    private final UserRepository userRepository;
    private final CartOutputService cartOutputService;

    public CartOutput execute(Integer productId, CartOperation operation, String email) {
        var user = userRepository.findUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        var cart = repository.getCartByUserId(user.getId());

        switch (operation) {
            case ADD -> cart.incrementItem(productId);
            case REMOVE -> cart.decrementItem(productId);
        }

        return cartOutputService.buildOutput(repository.saveCart(cart));
    }
}
