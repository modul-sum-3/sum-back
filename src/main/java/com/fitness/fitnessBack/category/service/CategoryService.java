package com.fitness.fitnessBack.category.service;

import com.fitness.fitnessBack.category.model.Category;
import com.fitness.fitnessBack.category.repository.CategoryRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Value
public class CategoryService {
    CategoryRepository categoryRepository;

    public List<Category> getAll() {
        List<Category> result = new ArrayList<>();
        result.addAll(categoryRepository.findAll());

        return result;
    }

    public Category getOne(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category deleteCategory(Long id) {
        Category result = categoryRepository.findById(id).orElseThrow();
        categoryRepository.delete(result);
        return result;
    }
}
