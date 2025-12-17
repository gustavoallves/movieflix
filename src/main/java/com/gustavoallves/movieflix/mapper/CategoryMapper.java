package com.gustavoallves.movieflix.mapper;

import com.gustavoallves.movieflix.controller.request.CategoryRequest;
import com.gustavoallves.movieflix.controller.response.CategoryResponse;
import com.gustavoallves.movieflix.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CategoryRequest request) {
        return Category
                .builder()
                .name(request.name())
                .build();
    }

    public static CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
