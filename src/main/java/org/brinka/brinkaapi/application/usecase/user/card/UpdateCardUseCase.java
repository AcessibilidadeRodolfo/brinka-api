package org.brinka.brinkaapi.application.usecase.user.card;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.application.dto.input.CardInput;
import org.brinka.brinkaapi.domain.exception.CardNotFoundException;
import org.brinka.brinkaapi.domain.exception.UserNotFoundException;
import org.brinka.brinkaapi.domain.model.Card;
import org.brinka.brinkaapi.domain.repository.CardRepository;
import org.brinka.brinkaapi.domain.repository.UserRepository;

@UseCase
@RequiredArgsConstructor
public class UpdateCardUseCase {
    private final CardRepository repository;
    private final UserRepository userRepository;

    public Card execute(CardInput cardInput, String email) {
        var user = userRepository.findUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        var card = repository.findCardByUser(user)
                .orElseThrow(() -> new CardNotFoundException(user.getEmail()));

        if (cardInput.cvc() != null)
            card.setCvc(cardInput.cvc());
        if (cardInput.dataValidade() != null)
            card.setDataValidade(cardInput.dataValidade());
        if (cardInput.nomeTitular() != null)
            card.setNomeTitular(cardInput.nomeTitular());
        if (cardInput.numeroCartao() != null)
            card.setNumeroCartao(cardInput.numeroCartao());

        return repository.saveCard(card);
    }
}