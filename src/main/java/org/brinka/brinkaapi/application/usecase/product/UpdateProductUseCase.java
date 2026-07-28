package org.brinka.brinkaapi.application.usecase.product;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.annotation.UseCase;
import org.brinka.brinkaapi.application.dto.UpdateProductUseCaseInput;
import org.brinka.brinkaapi.domain.exception.CategoryNotFoundException;
import org.brinka.brinkaapi.domain.exception.ProductNotFoundException;
import org.brinka.brinkaapi.domain.model.Product;
import org.brinka.brinkaapi.domain.repository.CategoryRepository;
import org.brinka.brinkaapi.domain.repository.ProductRepository;

@UseCase
@RequiredArgsConstructor
public class UpdateProductUseCase {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Product execute(UpdateProductUseCaseInput input) {
        var oldProduct = productRepository.findProductById(input.id())
                .orElseThrow(() -> new ProductNotFoundException(input.id()));

        var category = input.categoriaId() == null ?
                oldProduct.getCategoria() :
                categoryRepository.findById(input.categoriaId())
                        .orElseThrow(() -> new CategoryNotFoundException(input.categoriaId()));

        var newProduct = Product.builder()
                .id(oldProduct.getId())
                .imagem(input.imagem() == null ? oldProduct.getImagem() : input.imagem())
                .nome(input.nome() == null ? oldProduct.getNome() : input.nome())
                .categoria(category)
                .descricao(input.descricao() == null ? oldProduct.getDescricao() : input.descricao())
                .preco(input.preco() == null ? oldProduct.getPreco() : input.preco())
                .estoque(input.estoque() == null ? oldProduct.getEstoque() : input.estoque())
                .build();

        return oldProduct.equals(newProduct) ?
                oldProduct :
                productRepository.saveProduct(newProduct);
    }
}
