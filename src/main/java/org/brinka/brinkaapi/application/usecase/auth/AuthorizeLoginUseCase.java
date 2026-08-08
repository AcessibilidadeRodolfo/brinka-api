package org.brinka.brinkaapi.application.usecase.auth;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.domain.model.User;
import org.brinka.brinkaapi.infra.persistence.entity.UserEntity;
import org.brinka.brinkaapi.infra.persistence.mapper.ProductMapper;
import org.brinka.brinkaapi.infra.persistence.mapper.UserMapper;
import org.brinka.brinkaapi.infra.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@UseCase
@RequiredArgsConstructor
public class AuthorizeLoginUseCase {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserMapper mapper;

    public String execute(String login, String senha) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(login, senha);
        var auth = authenticationManager.authenticate(usernamePassword);

        var userEntity = (UserEntity) auth.getPrincipal();

        return tokenService.generateToken(mapper.toDomain(userEntity));
    }
}
