package org.brinka.brinkaapi.application.usecase.user.profile;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.domain.exception.UserNotFoundException;
import org.brinka.brinkaapi.domain.model.User;
import org.brinka.brinkaapi.domain.repository.UserRepository;

@UseCase
@RequiredArgsConstructor
public class GetUserProfileUseCase {
    private final UserRepository userRepository;

    public User execute(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }
}
