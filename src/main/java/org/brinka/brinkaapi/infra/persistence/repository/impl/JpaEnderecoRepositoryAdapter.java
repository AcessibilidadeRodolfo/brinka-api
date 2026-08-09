package org.brinka.brinkaapi.infra.persistence.repository.impl;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.domain.model.Endereco;
import org.brinka.brinkaapi.domain.model.User;
import org.brinka.brinkaapi.domain.repository.EnderecoRepository;
import org.brinka.brinkaapi.infra.persistence.mapper.EnderecoMapper;
import org.brinka.brinkaapi.infra.persistence.mapper.UserMapper;
import org.brinka.brinkaapi.infra.persistence.repository.JpaEnderecoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaEnderecoRepositoryAdapter implements EnderecoRepository {
    private final JpaEnderecoRepository jpaRepository;
    private final UserMapper userMapper;
    private final EnderecoMapper mapper;

    @Override
    public Optional<Endereco> findEnderecoByUser(User user) {
        return jpaRepository.findByUsuario(userMapper.toEntity(user))
                .map(mapper::toDomain);
    }

    @Override
    public Endereco save(Endereco endereco) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(endereco)));
    }
}
