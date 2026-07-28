package org.brinka.brinkaapi.application.gateway;

import org.brinka.brinkaapi.domain.model.User;

public interface TokenGateway {
    String generateToken(User user);
    String validateToken(String token);
}
