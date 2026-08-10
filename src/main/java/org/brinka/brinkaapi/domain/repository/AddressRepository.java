package org.brinka.brinkaapi.domain.repository;

import org.brinka.brinkaapi.domain.model.Address;
import org.brinka.brinkaapi.domain.model.User;

import java.util.Optional;

public interface AddressRepository {
    Optional<Address> findAddressByUser(User user);
    Address save(Address address);
}
