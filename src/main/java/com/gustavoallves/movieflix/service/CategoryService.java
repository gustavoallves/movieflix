package com.gustavoallves.movieflix.service;

import com.gustavoallves.movieflix.controller.request.CategoryRequest;
import com.gustavoallves.movieflix.controller.response.CategoryResponse;
import com.gustavoallves.movieflix.entity.Category;
import com.gustavoallves.movieflix.mapper.CategoryMapper;
import com.gustavoallves.movieflix.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = CategoryMapper.toCategory(categoryRequest);
        repository.save(category);
        return CategoryMapper.toCategoryResponse(category);
    }

    public Optional<CategoryResponse> findById(Long id) {
        return repository.findById(id)
                .map(CategoryMapper::toCategoryResponse);
    }

    public List<CategoryResponse> findAll() {
        List<Category> categories = repository.findAll();
        return categories.stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
    }

    public void deleteCategoryById(Long id) {
        repository.deleteById(id);
    }
}
