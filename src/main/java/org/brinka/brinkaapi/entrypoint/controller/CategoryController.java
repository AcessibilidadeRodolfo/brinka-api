package org.brinka.brinkaapi.entrypoint.controller;

import lombok.RequiredArgsConstructor;
import org.brinka.brinkaapi.domain.model.Category;
import org.brinka.brinkaapi.domain.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categorias")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }
}
