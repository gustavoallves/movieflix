package com.gustavoallves.movieflix.service;

import com.gustavoallves.movieflix.entity.Category;
import com.gustavoallves.movieflix.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public Optional<Category> findById(Long id) {
        return repository.findById(id);
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category saveCategory(Category category) {
        return repository.save(category);
    }

    public void deleteCategoryById(Long id) {
        repository.deleteById(id);
    }
}
