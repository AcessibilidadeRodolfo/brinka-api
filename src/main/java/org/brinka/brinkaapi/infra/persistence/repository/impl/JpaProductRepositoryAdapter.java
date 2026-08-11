package org.brinka.brinkaapi.infra.persistence.repository.impl;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.domain.model.Product;
import org.brinka.brinkaapi.domain.repository.ProductRepository;
import org.brinka.brinkaapi.infra.persistence.mapper.ProductMapper;
import org.brinka.brinkaapi.infra.persistence.repository.JpaProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaProductRepositoryAdapter implements ProductRepository {
    private final JpaProductRepository jpaRepository;
    private final ProductMapper mapper;

    @Override
    public List<Product> findProducts() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Product saveProduct(Product product) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(product)));
    }

    @Override
    public List<Product> saveProducts(List<Product> products) {
        return jpaRepository.saveAll(
            products.stream()
                    .map(mapper::toEntity)
                    .toList()
        )
        .stream()
        .map(mapper::toDomain)
        .toList();
    }

    @Override
    public Optional<Product> findProductById(Integer id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Product> findProductByIdWithAvaliacoes(Integer id) {
        return jpaRepository.findWithAvaliacoesById(id).map(mapper::toDomainWithReviews);
    }

    @Override
    public List<Product> findProductsById(List<Integer> id) {
        return jpaRepository.findAllById(id).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteProductsById(List<Integer> ids) {
        jpaRepository.deleteAllById(ids);
    }

    @Override
    public void deleteProductById(Integer id) {
        jpaRepository.deleteById(id);
    }
}
