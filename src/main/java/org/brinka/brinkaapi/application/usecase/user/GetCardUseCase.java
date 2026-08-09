package org.brinka.brinkaapi.application.usecase.user;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.domain.exception.CardNotFoundException;
import org.brinka.brinkaapi.domain.exception.UserNotFoundException;
import org.brinka.brinkaapi.domain.model.Card;
import org.brinka.brinkaapi.domain.repository.CardRepository;
import org.brinka.brinkaapi.domain.repository.UserRepository;

@UseCase
@RequiredArgsConstructor
public class GetCardUseCase {
    private final CardRepository repository;
    private final UserRepository userRepository;

    public Card execute(String email) {
        var user = userRepository.findUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        return repository.findCardByUser(user)
                .orElseThrow(() -> new CardNotFoundException(user.getEmail()));
    }
}
