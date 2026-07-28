package org.brinka.brinkaapi.application.usecase.auth;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.domain.model.User;
import org.brinka.brinkaapi.infra.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@UseCase
@RequiredArgsConstructor
public class AuthorizeLoginUseCase {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public String execute(String login, String senha) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(login, senha);
        var auth = authenticationManager.authenticate(usernamePassword);

        return tokenService.generateToken((User) auth.getPrincipal());
    }
}
