package org.brinka.brinkaapi.domain.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Integer id) {
        super("Category not found with id: " + id);
    }
}
