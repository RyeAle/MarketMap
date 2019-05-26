package ru.gatchina.marketmap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gatchina.marketmap.domain.Category;
import ru.gatchina.marketmap.repository.CategoryRepository;

@RestController
public class ProductController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/category")
    public Iterable<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
