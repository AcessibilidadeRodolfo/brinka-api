package org.brinka.brinkaapi.infra.persistence.repository;

import org.brinka.brinkaapi.infra.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);
}
