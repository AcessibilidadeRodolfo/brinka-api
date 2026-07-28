package org.brinka.brinkaapi.application.usecase.product;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.domain.exception.ProductNotFoundException;
import org.brinka.brinkaapi.domain.repository.ProductRepository;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class DeleteProductsByIdUseCase {
    private final ProductRepository productRepository;

    public void execute(List<Integer> ids) {
        ids.forEach(id ->
                productRepository.findProductById(id)
                        .orElseThrow(() -> new ProductNotFoundException(id))
        );

        productRepository.deleteProductsById(ids);
    }
}
