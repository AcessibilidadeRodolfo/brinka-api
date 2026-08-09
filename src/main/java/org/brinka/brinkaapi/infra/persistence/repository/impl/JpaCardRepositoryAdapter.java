package org.brinka.brinkaapi.infra.persistence.repository.impl;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.domain.model.Card;
import org.brinka.brinkaapi.domain.model.User;
import org.brinka.brinkaapi.domain.repository.CardRepository;
import org.brinka.brinkaapi.infra.persistence.mapper.CardMapper;
import org.brinka.brinkaapi.infra.persistence.mapper.UserMapper;
import org.brinka.brinkaapi.infra.persistence.repository.JpaCardRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaCardRepositoryAdapter implements CardRepository {
    private final JpaCardRepository jpaRepository;
    private final UserMapper userMapper;
    private final CardMapper mapper;

    @Override
    public Card saveCard(Card card) {
        var cardEntity = mapper.toEntity(card);
        return mapper.toDomain(jpaRepository.save(cardEntity));
    }

    @Override
    public Optional<Card> findCardByUser(User user) {
        return jpaRepository.findByUsuario(userMapper.toEntity(user)).map(mapper::toDomain);
    }
}
