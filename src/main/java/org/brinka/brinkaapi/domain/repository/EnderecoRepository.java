package org.brinka.brinkaapi.domain.repository;

import org.brinka.brinkaapi.domain.model.Endereco;
import org.brinka.brinkaapi.domain.model.User;

import java.util.Optional;

public interface EnderecoRepository {
    Optional<Endereco> findEnderecoByUser(User user);
    Endereco save(Endereco endereco);
}
