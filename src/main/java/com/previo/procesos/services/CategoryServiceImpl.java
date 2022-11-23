package com.previo.procesos.services;

import com.previo.procesos.models.Category;
import com.previo.procesos.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity createCategory(@RequestBody Category category) {
        try {
            categoryRepository.save(category);
            return new ResponseEntity(category, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Category> updateCategory(Long id, Category category) {
        Optional<Category> categoryFind = categoryRepository.findById(id);
        if (categoryFind.isPresent()) {
            try {
                categoryFind.get().setName(category.getName());
                categoryFind.get().setDescription(category.getDescription());
                categoryRepository.save(categoryFind.get());
                return new ResponseEntity(category, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Category> deleteCategory(Long id) {
        Optional<Category> categoryFind = categoryRepository.findById(id);
        if (categoryFind.isPresent()) {
            categoryRepository.delete(categoryFind.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
