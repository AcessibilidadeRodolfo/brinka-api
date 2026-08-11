package org.brinka.brinkaapi.application.usecase.product;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.domain.exception.ProductNotFoundException;
import org.brinka.brinkaapi.domain.model.Product;
import org.brinka.brinkaapi.domain.repository.ProductRepository;

@UseCase
@RequiredArgsConstructor
public class GetProductByIdUseCase {
    private final ProductRepository productRepository;

    public Product execute(Integer id, Boolean avaliacoes) {
        if (Boolean.TRUE.equals(avaliacoes))
            return productRepository.findProductByIdWithAvaliacoes(id)
                    .orElseThrow(() -> new ProductNotFoundException(id));
        else
            return productRepository.findProductById(id)
                    .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
