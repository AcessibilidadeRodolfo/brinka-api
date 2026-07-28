package org.brinka.brinkaapi.application.usecase.product;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.domain.exception.ProductNotFoundException;
import org.brinka.brinkaapi.domain.repository.ProductRepository;

import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional
public class DeleteProductsByIdUseCase {
    private final ProductRepository productRepository;

    public void execute(List<Integer> ids) {
        productRepository.findProductsById(ids);

        productRepository.deleteProductsById(ids);
    }
}
