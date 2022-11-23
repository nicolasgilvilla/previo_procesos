package com.previo.procesos.services;

import com.previo.procesos.models.Category;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

    ResponseEntity<Category> createCategory(Category category);

    ResponseEntity<Category> updateCategory(Long id, Category category);

    ResponseEntity<Category> deleteCategory(Long id);

}
