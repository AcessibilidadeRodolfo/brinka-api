package org.brinka.brinkaapi.infra.persistence.repository;

import org.brinka.brinkaapi.infra.persistence.entity.CardEntity;
import org.brinka.brinkaapi.infra.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaCardRepository extends JpaRepository<CardEntity, Integer> {
    Optional<CardEntity> findByUsuario(UserEntity usuario);
}
