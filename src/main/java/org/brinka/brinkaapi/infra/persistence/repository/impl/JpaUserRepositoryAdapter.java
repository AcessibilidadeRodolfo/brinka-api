package org.brinka.brinkaapi.infra.persistence.repository.impl;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.domain.model.User;
import org.brinka.brinkaapi.domain.repository.UserRepository;
import org.brinka.brinkaapi.infra.persistence.mapper.UserMapper;
import org.brinka.brinkaapi.infra.persistence.repository.JpaUserRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaUserRepositoryAdapter implements UserRepository {
    private final JpaUserRepository jpaRepository;
    private final UserMapper mapper;

    @Override
    public User findUserByEmail(String email) {
        return mapper.toDomain(jpaRepository.findByEmail(email));
    }

    @Override
    public User saveUser(User user) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(user)));
    }
}
