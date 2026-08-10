package org.brinka.brinkaapi.infra.persistence.repository.impl;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.domain.model.Address;
import org.brinka.brinkaapi.domain.model.User;
import org.brinka.brinkaapi.domain.repository.AddressRepository;
import org.brinka.brinkaapi.infra.persistence.mapper.AddressMapper;
import org.brinka.brinkaapi.infra.persistence.mapper.UserMapper;
import org.brinka.brinkaapi.infra.persistence.repository.JpaAddressRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaAddressRepositoryAdapter implements AddressRepository {
    private final JpaAddressRepository jpaRepository;
    private final UserMapper userMapper;
    private final AddressMapper mapper;

    @Override
    public Optional<Address> findAddressByUser(User user) {
        return jpaRepository.findByUsuario(userMapper.toEntity(user))
                .map(mapper::toDomain);
    }

    @Override
    public Address save(Address address) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(address)));
    }
}
