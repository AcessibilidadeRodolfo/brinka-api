package org.brinka.brinkaapi.domain.repository;

import org.brinka.brinkaapi.domain.model.User;

public interface UserRepository {
    User findUserByEmail(String email);
    User saveUser(User user);
}
