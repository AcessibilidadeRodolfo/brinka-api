package org.brinka.brinkaapi.domain.repository;

import org.brinka.brinkaapi.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product saveProduct(Product product);
    List<Product> saveProducts(List<Product> product);
    List<Product> findProducts();
    Optional<Product> findProductById(Integer id);
    void deleteProductsById(List<Integer> ids);
    void deleteProductById(Integer id);
}
