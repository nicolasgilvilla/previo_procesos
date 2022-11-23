package com.previo.procesos.controllers;

import com.previo.procesos.models.CategoryModel;
import com.previo.procesos.services.CategoryService;
import com.previo.procesos.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping(value = "/category")
    public ResponseEntity createCategory(@RequestBody CategoryModel categoryModel, @RequestHeader(value = "Authorization") String token) {
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalid");
        }
        return categoryService.createCategory(categoryModel);
    }

    @PutMapping(value = "/category/{id}")
    public ResponseEntity updateCategory(@PathVariable Long id, @RequestBody CategoryModel categoryModel, @RequestHeader(value = "Authorization") String token) {
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalid");
        }
        return categoryService.updateCategory(id, categoryModel);
    }

    @DeleteMapping(value = "/category/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id, @RequestHeader(value = "Authorization") String token) {
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalid");
        }
        return categoryService.deleteCategory(id);
    }
}
