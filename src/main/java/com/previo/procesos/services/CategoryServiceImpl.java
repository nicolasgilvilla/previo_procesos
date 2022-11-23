package com.previo.procesos.services;

import com.previo.procesos.models.CategoryModel;
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
    public ResponseEntity createCategory(@RequestBody CategoryModel categoryModel) {
        try {
            categoryRepository.save(categoryModel);
            return new ResponseEntity(categoryModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<CategoryModel> updateCategory(Long id, CategoryModel categoryModel) {
        Optional<CategoryModel> categoryFind = categoryRepository.findById(id);
        if (categoryFind.isPresent()) {
            try {
                categoryFind.get().setName(categoryModel.getName());
                categoryFind.get().setDescription(categoryModel.getDescription());
                categoryRepository.save(categoryFind.get());
                return new ResponseEntity(categoryModel, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<CategoryModel> deleteCategory(Long id) {
        Optional<CategoryModel> categoryFind = categoryRepository.findById(id);
        if (categoryFind.isPresent()) {
            categoryRepository.delete(categoryFind.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
