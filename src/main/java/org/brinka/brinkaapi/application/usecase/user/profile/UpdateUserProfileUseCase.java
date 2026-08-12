package org.brinka.brinkaapi.application.usecase.user.profile;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.application.dto.input.UserProfileInput;
import org.brinka.brinkaapi.domain.exception.UserNotFoundException;
import org.brinka.brinkaapi.domain.model.User;
import org.brinka.brinkaapi.domain.repository.UserRepository;

@UseCase
@RequiredArgsConstructor
public class UpdateUserProfileUseCase {
    private final UserRepository userRepository;

    public User execute(UserProfileInput input, String email) {
        var user = userRepository.findUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        if (input.email() != null)
            user.setEmail(input.email());
        if (input.nome() != null)
            user.setNome(input.nome());
        if (input.telefone() != null)
            user.setTelefone(input.telefone());

        return userRepository.saveUser(user);
    }
}
