package org.brinka.brinkaapi.application.usecase.product;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.application.dto.AddProductUseCaseInput;
import org.brinka.brinkaapi.domain.exception.CategoryNotFoundException;
import org.brinka.brinkaapi.domain.model.Product;
import org.brinka.brinkaapi.domain.repository.CategoryRepository;
import org.brinka.brinkaapi.domain.repository.ProductRepository;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class AddProductsUseCase {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<Product> execute(List<AddProductUseCaseInput> inputs) {
        List<Product> products = inputs.stream()
                .map(this::toProduct)
                .toList();

        return productRepository.saveProducts(products);
    }

    private Product toProduct(AddProductUseCaseInput input) {
        var category = categoryRepository.findById(input.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException(input.categoryId())) ;

        return Product.builder()
                .id(null)
                .imagem(input.imagem())
                .nome(input.nome())
                .categoria(category)
                .descricao(input.descricao())
                .preco(input.preco())
                .estoque(input.estoque())
                .build();
    }
}
