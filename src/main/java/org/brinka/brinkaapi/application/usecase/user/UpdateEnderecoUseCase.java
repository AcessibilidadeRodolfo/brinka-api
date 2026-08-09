package org.brinka.brinkaapi.application.usecase.user;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.application.dto.EnderecoInput;
import org.brinka.brinkaapi.domain.exception.EnderecoNotFoundException;
import org.brinka.brinkaapi.domain.exception.UserNotFoundException;
import org.brinka.brinkaapi.domain.model.Endereco;
import org.brinka.brinkaapi.domain.repository.EnderecoRepository;
import org.brinka.brinkaapi.domain.repository.UserRepository;

@UseCase
@RequiredArgsConstructor
public class UpdateEnderecoUseCase {
    private final EnderecoRepository repository;
    private final UserRepository userRepository;

    public Endereco execute(EnderecoInput input, String email) {
        var user = userRepository.findUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        var endereco = repository.findEnderecoByUser(user)
                .orElseThrow(() -> new EnderecoNotFoundException(user.getEmail()));

        if (input.cep() != null)
            endereco.setCep(input.cep());
        if (input.cidade() != null)
            endereco.setCidade(input.cidade());
        if (input.complemento() != null)
            endereco.setComplemento(input.complemento());
        if (input.estado() != null)
            endereco.setEstado(input.estado());
        if (input.numero() != null)
            endereco.setNumero(input.numero());
        if (input.rua() != null)
            endereco.setRua(input.rua());

        return repository.save(endereco);
    }
}
