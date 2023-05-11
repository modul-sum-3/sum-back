package com.fitness.fitnessBack.category.controller;

import com.fitness.fitnessBack.category.model.Category;
import com.fitness.fitnessBack.category.service.CategoryService;

import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Value
@CrossOrigin
@RequestMapping(value = "/categorys", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {
    CategoryService categoryService;

    @GetMapping
    public List<Category> findAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public Category findOne(@PathVariable(value = "id") Long id) {
        return categoryService.getOne(id);
    }

    @PostMapping
    public Category saveCategory(@Valid @RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @DeleteMapping("/{id}")
    public Category deleteCategory(@PathVariable(value = "id") Long id) {
        return categoryService.deleteCategory(id);
    }
}
