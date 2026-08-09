package org.brinka.brinkaapi.application.usecase.user.endereco;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.domain.exception.UserNotFoundException;
import org.brinka.brinkaapi.domain.model.Address;
import org.brinka.brinkaapi.domain.exception.AddressNotFoundException;
import org.brinka.brinkaapi.domain.repository.AddressRepository;
import org.brinka.brinkaapi.domain.repository.UserRepository;

@UseCase
@RequiredArgsConstructor
public class GetAddressUseCase {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public Address execute(String email) {
        var user = userRepository.findUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        return addressRepository.findAddressByUser(user)
                .orElseThrow(() -> new AddressNotFoundException(user.getEmail()));
    }
}
