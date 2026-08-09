package org.brinka.brinkaapi.application.usecase.user;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.domain.exception.UserNotFoundException;
import org.brinka.brinkaapi.domain.model.Endereco;
import org.brinka.brinkaapi.domain.exception.EnderecoNotFoundException;
import org.brinka.brinkaapi.domain.repository.EnderecoRepository;
import org.brinka.brinkaapi.domain.repository.UserRepository;

@UseCase
@RequiredArgsConstructor
public class GetEnderecoUseCase {
    private final EnderecoRepository enderecoRepository;
    private final UserRepository userRepository;

    public Endereco execute(String email) {
        var user = userRepository.findUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        return enderecoRepository.findEnderecoByUser(user)
                .orElseThrow(() -> new EnderecoNotFoundException(user.getEmail()));
    }
}
