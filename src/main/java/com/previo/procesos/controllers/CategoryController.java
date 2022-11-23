package com.previo.procesos.controllers;

import com.previo.procesos.models.CategoryModel;
import com.previo.procesos.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/category")
    public ResponseEntity createCategory(@RequestBody CategoryModel categoryModel) {
        return categoryService.createCategory(categoryModel);
    }

    @PutMapping(value = "/category/{id}")
    public ResponseEntity updateCategory(@PathVariable Long id, @RequestBody CategoryModel categoryModel) {
        return categoryService.updateCategory(id, categoryModel);
    }

    @DeleteMapping(value = "/category/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }
}
