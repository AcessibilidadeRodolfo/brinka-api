package org.brinka.brinkaapi.infra.persistence.repository;

import org.brinka.brinkaapi.infra.persistence.entity.AddressEntity;
import org.brinka.brinkaapi.infra.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaAddressRepository extends JpaRepository<AddressEntity, Integer> {
    Optional<AddressEntity> findByUsuario(UserEntity usuario);
}
