package org.brinka.brinkaapi.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.gateway.TokenGateway;
import org.brinka.brinkaapi.domain.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TokenService implements TokenGateway {
    @Value("${spring.security.token.secret}")
    private String applicationSecret;

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(applicationSecret);
        return JWT.create()
                .withIssuer("brinka-auth")
                .withSubject(user.getEmail())
                .withExpiresAt(generateExpirationDate())
                .sign(algorithm);
    }

    public String validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(applicationSecret);
        return JWT.require(algorithm)
                .withIssuer("brinka-auth")
                .build()
                .verify(token)
                .getSubject();
    }

    private Instant generateExpirationDate() {
        return Instant.now().plus(Duration.ofHours(2));
    }
}
