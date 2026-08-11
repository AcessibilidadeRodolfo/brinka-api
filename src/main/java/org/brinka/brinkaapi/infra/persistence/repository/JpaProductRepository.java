package org.brinka.brinkaapi.infra.persistence.repository;

import org.brinka.brinkaapi.infra.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Integer> {
    @EntityGraph(value = "Product.avaliacoes")
    List<ProductEntity> findWithAvaliacoesById(Integer id);
}
