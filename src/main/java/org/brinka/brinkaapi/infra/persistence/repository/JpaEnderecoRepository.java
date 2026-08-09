package org.brinka.brinkaapi.infra.persistence.repository;

import org.brinka.brinkaapi.infra.persistence.entity.EnderecoEntity;
import org.brinka.brinkaapi.infra.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaEnderecoRepository extends JpaRepository<EnderecoEntity, Integer> {
    Optional<EnderecoEntity> findByUsuario(UserEntity usuario);
}
