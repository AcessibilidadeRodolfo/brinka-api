package org.brinka.brinkaapi.domain.repository;

import org.brinka.brinkaapi.domain.model.Card;
import org.brinka.brinkaapi.domain.model.User;

import java.util.Optional;

public interface CardRepository {
    Card saveCard(Card card);
    Optional<Card> findCardByUser(User user);
}
