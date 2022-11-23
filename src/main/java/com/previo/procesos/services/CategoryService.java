package com.previo.procesos.services;

import com.previo.procesos.models.CategoryModel;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

    ResponseEntity<CategoryModel> createCategory(CategoryModel categoryModel);

    ResponseEntity<CategoryModel> updateCategory(Long id, CategoryModel categoryModel);

    ResponseEntity<CategoryModel> deleteCategory(Long id);

}
