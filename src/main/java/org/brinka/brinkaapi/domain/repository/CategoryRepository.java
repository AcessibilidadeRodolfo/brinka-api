package org.brinka.brinkaapi.domain.repository;

import org.brinka.brinkaapi.domain.model.Category;

import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findById(Integer id);
}
