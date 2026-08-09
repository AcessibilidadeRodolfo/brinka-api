package org.brinka.brinkaapi.domain.repository;

import org.brinka.brinkaapi.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserByEmail(String email);
    User saveUser(User user);
}
