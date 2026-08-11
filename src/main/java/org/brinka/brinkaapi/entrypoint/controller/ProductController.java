package org.brinka.brinkaapi.entrypoint.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.application.usecase.product.*;
import org.brinka.brinkaapi.entrypoint.dto.request.PatchProductRequest;
import org.brinka.brinkaapi.entrypoint.dto.request.ProductRequest;
import org.brinka.brinkaapi.entrypoint.dto.response.ProductResponse;
import org.brinka.brinkaapi.entrypoint.mapper.RequestMapper;
import org.brinka.brinkaapi.entrypoint.mapper.ResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final AddProductsUseCase addProductsUseCase;
    private final AddProductUseCase addProductUseCase;
    private final DeleteProductsByIdUseCase deleteProductsByIdUseCase;
    private final DeleteProductByIdUseCase deleteProductByIdUseCase;
    private final RequestMapper requestMapper;
    private final ResponseMapper responseMapper;
    private final GetProductByIdUseCase findProductByIdUseCase;
    private final UpdateProductUseCase updateProductUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable Integer id, @RequestParam(required = false) Boolean avaliacoes) {
        return ResponseEntity.ok(responseMapper.toResponse(findProductByIdUseCase.execute(id, avaliacoes)));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok(responseMapper.toResponseList(getAllProductsUseCase.execute()));
    }

    @PostMapping
    public ResponseEntity<List<ProductResponse>> saveProducts(@RequestBody @Valid List<ProductRequest> productRequests) {
        var response = addProductsUseCase.execute(requestMapper.toInputList(productRequests));

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMapper.toResponseList(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable @NotNull Integer id) {
        deleteProductByIdUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProducts(@RequestParam List<Integer> ids) {
        deleteProductsByIdUseCase.execute(ids);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> patchProduct(@PathVariable Integer id, @RequestBody @Valid PatchProductRequest productRequest) {
        var response = updateProductUseCase.execute(requestMapper.toInput(id, productRequest));
        return ResponseEntity.ok(responseMapper.toResponse(response));
    }
}
