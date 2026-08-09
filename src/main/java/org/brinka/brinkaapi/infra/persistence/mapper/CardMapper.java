package org.brinka.brinkaapi.infra.persistence.mapper;

import org.brinka.brinkaapi.domain.model.Card;
import org.brinka.brinkaapi.infra.persistence.entity.CardEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface CardMapper {
    Card toDomain(CardEntity entity);
    CardEntity toEntity(Card domain);
}
