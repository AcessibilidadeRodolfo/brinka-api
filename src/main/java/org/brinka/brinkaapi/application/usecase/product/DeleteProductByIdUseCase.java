package org.brinka.brinkaapi.application.usecase.product;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.domain.exception.ProductNotFoundException;
import org.brinka.brinkaapi.domain.repository.ProductRepository;

@UseCase
@RequiredArgsConstructor
public class DeleteProductByIdUseCase {
    private final ProductRepository productRepository;

    public void execute(Integer id) {
        productRepository.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        productRepository.deleteProductById(id);
    }
}
