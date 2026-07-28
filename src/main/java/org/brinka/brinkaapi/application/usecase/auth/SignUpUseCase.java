package org.brinka.brinkaapi.application.usecase.auth;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.application.dto.SignUpUseCaseInput;
import org.brinka.brinkaapi.domain.exception.EmailAlreadyExistsException;
import org.brinka.brinkaapi.domain.model.User;
import org.brinka.brinkaapi.domain.repository.UserRepository;
import org.brinka.brinkaapi.infra.security.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;

@UseCase
@RequiredArgsConstructor
public class SignUpUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public String execute(SignUpUseCaseInput signUpUseCaseInput) {
        if (userRepository.findUserByEmail(signUpUseCaseInput.email()) != null)
            throw new EmailAlreadyExistsException(signUpUseCaseInput.email());

        String hashedPassword = passwordEncoder.encode(signUpUseCaseInput.senha());
        User user = User.builder()
                .nome(signUpUseCaseInput.nome())
                .email(signUpUseCaseInput.email())
                .telefone(signUpUseCaseInput.telefone())
                .senha(hashedPassword)
                .ehAdmin(false)
                .build();

        userRepository.saveUser(user);

        return tokenService.generateToken(user);
    }
}
