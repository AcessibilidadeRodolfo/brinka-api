package org.brinka.brinkaapi.application.usecase.product;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.application.dto.AddProductUseCaseInput;
import org.brinka.brinkaapi.domain.exception.CategoryNotFoundException;
import org.brinka.brinkaapi.domain.model.Product;
import org.brinka.brinkaapi.domain.repository.CategoryRepository;
import org.brinka.brinkaapi.domain.repository.ProductRepository;

@UseCase
@RequiredArgsConstructor
public class AddProductUseCase {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Product execute(AddProductUseCaseInput input) {
        var category = categoryRepository.findById(input.categoriaId())
                .orElseThrow(() -> new CategoryNotFoundException(input.categoriaId()));

        var product = Product.builder()
                .id(null)
                .imagem(input.imagem())
                .nome(input.nome())
                .categoria(category)
                .descricao(input.descricao())
                .preco(input.preco())
                .estoque(input.estoque())
                .build();

        return productRepository.saveProduct(product);
    }
}
