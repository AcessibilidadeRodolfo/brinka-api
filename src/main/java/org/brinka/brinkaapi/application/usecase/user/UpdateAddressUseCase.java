package org.brinka.brinkaapi.application.usecase.user;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.application.dto.AddressInput;
import org.brinka.brinkaapi.domain.exception.AddressNotFoundException;
import org.brinka.brinkaapi.domain.exception.UserNotFoundException;
import org.brinka.brinkaapi.domain.model.Address;
import org.brinka.brinkaapi.domain.repository.AddressRepository;
import org.brinka.brinkaapi.domain.repository.UserRepository;

@UseCase
@RequiredArgsConstructor
public class UpdateAddressUseCase {
    private final AddressRepository repository;
    private final UserRepository userRepository;

    public Address execute(AddressInput input, String email) {
        var user = userRepository.findUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        var address = repository.findAddressByUser(user)
                .orElseThrow(() -> new AddressNotFoundException(user.getEmail()));

        if (input.cep() != null)
            address.setCep(input.cep());
        if (input.cidade() != null)
            address.setCidade(input.cidade());
        if (input.complemento() != null)
            address.setComplemento(input.complemento());
        if (input.estado() != null)
            address.setEstado(input.estado());
        if (input.numero() != null)
            address.setNumero(input.numero());
        if (input.rua() != null)
            address.setRua(input.rua());

        return repository.save(address);
    }
}
