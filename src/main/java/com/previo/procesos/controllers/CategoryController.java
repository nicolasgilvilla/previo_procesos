package com.previo.procesos.controllers;

import com.previo.procesos.models.Category;
import com.previo.procesos.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/category")
    public ResponseEntity createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @PutMapping(value = "/category/{id}")
    public ResponseEntity updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping(value = "/category/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }
}
