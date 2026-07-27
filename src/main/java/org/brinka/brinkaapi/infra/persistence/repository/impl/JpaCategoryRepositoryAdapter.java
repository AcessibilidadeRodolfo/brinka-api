package org.brinka.brinkaapi.infra.persistence.repository.impl;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.domain.model.Category;
import org.brinka.brinkaapi.domain.repository.CategoryRepository;
import org.brinka.brinkaapi.infra.persistence.mapper.CategoryMapper;
import org.brinka.brinkaapi.infra.persistence.repository.JpaCategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaCategoryRepositoryAdapter implements CategoryRepository {
    private final JpaCategoryRepository jpaRepository;
    private final CategoryMapper mapper;

    @Override
    public Optional<Category> findById(Integer id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
}
