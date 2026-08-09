package org.brinka.brinkaapi.application.usecase.user;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.application.dto.CardInput;
import org.brinka.brinkaapi.domain.exception.UserNotFoundException;
import org.brinka.brinkaapi.domain.model.Card;
import org.brinka.brinkaapi.domain.repository.CardRepository;
import org.brinka.brinkaapi.domain.repository.UserRepository;

@UseCase
@RequiredArgsConstructor
public class AddCardUseCase {
    private final CardRepository repository;
    private final UserRepository userRepository;

    public Card execute(CardInput cardInput, String email) {
        var user = userRepository.findUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        var card = Card.builder()
                .usuario(user)
                .numeroCartao(cardInput.numeroCartao())
                .nomeTitular(cardInput.nomeTitular())
                .dataValidade(cardInput.dataValidade())
                .cvc(cardInput.cvc())
                .build();

        return repository.saveCard(card);
    }
}
