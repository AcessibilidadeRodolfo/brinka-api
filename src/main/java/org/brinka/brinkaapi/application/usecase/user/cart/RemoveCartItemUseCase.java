package org.brinka.brinkaapi.application.usecase.user.cart;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.domain.exception.UserNotFoundException;
import org.brinka.brinkaapi.domain.repository.CartRepository;
import org.brinka.brinkaapi.domain.repository.UserRepository;

@UseCase
@RequiredArgsConstructor
public class RemoveCartItemUseCase {
    private final CartRepository repository;
    private final UserRepository userRepository;
    public void execute(Integer productId, String email) {
        var user = userRepository.findUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        var cart = repository.getCartByUserId(user.getId());

        cart.removeItem(productId);

        repository.saveCart(cart);
    }
}
