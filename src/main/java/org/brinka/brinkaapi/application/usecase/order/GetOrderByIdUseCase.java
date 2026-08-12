package org.brinka.brinkaapi.application.usecase.order;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.domain.exception.OrderNotFoundException;
import org.brinka.brinkaapi.domain.exception.UserNotFoundException;
import org.brinka.brinkaapi.domain.model.Order;
import org.brinka.brinkaapi.domain.repository.OrderRepository;
import org.brinka.brinkaapi.domain.repository.UserRepository;

@UseCase
@RequiredArgsConstructor
public class GetOrderByIdUseCase {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public Order execute(Integer id, String email) {
        var user = userRepository.findUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        return orderRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }
}
