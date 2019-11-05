package com.netflix.catalog.service;

import com.netflix.catalog.entity.CategoryEntity;
import com.netflix.catalog.exception.CatalogException;
import com.netflix.catalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void verifyCategoriesExists(final List<CategoryEntity> categories) {
        categories.stream()
            .filter(category -> !categoryRepository.existsById(category.getId()))
            .findAny()
            .ifPresent(category -> {
                final String message = "Category %d not found";
                throw new CatalogException(HttpStatus.NOT_FOUND, String.format(message, category.getId()));
            });
    }
}
