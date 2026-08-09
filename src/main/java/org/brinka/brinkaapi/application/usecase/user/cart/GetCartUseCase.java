package org.brinka.brinkaapi.application.usecase.user.cart;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.domain.exception.UserNotFoundException;
import org.brinka.brinkaapi.domain.model.Cart;
import org.brinka.brinkaapi.domain.repository.CartRepository;
import org.brinka.brinkaapi.domain.repository.UserRepository;

@UseCase
@RequiredArgsConstructor
public class GetCartUseCase {
    private final CartRepository repository;
    private final UserRepository userRepository;

    public Cart execute(String email) {
        var user = userRepository.findUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        return repository.getCartByUserId(user.getId());
    }
}
